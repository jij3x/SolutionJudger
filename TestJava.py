"""
-d Clear the stage. All java related files will be deleted.
-c Cleanup generated files (Driver, Solution). All other java files and class files will stay.
-s Reset the stage. Prepare all java files, and compile them.

<dir>  Test the problem inside provided directory
-all   Test all problems in all problem directories
-quick Use existing compiled class, instead of starting from scratch.
-debug Leave Driver, and, Solution.
"""

import subprocess
import sys
import os
import os.path
import shutil
import json
import judges


PYCMD = "python3"
P1PROB_PATH = "_p1_problems"
PROB_PATH = "_problems"
GENR_PATH = "JavaGenerator"


def cleanup_gens():
    for fname in os.listdir("."):
        if fname in ("Driver.java", "Solution.java", "Driver.class", "Solution.class", "user.out", "user.err"):
            os.remove(fname)


def clear_stage():
    cleanup_gens()
    for fname in os.listdir("."):
        if fname.endswith(".class") or fname.endswith(".java"):
            os.remove(fname)


def setup_stage():
    clear_stage()
    for fname in os.listdir(GENR_PATH):
        if fname.endswith(".java"):
            shutil.copy(os.path.join(GENR_PATH, fname), ".")
    for fname in os.listdir("."):
        if fname.endswith(".java"):
            subprocess.call(["javac", fname])


def test_problem(problem_path, debug):
    #
    # Find problem metadata
    #
    metadata_fname = ""
    for file in os.listdir(problem_path):
        if file.endswith(".json"):
            metadata_fname = os.path.join(problem_path, file)
            break

    #
    # Generate Driver.java, and decide on judge
    #
    with open("Driver.java", "w") as driver, open(metadata_fname, "r") as metadata:
        subprocess.call([PYCMD, os.path.join(GENR_PATH, "gen_java_driver.py")], stdin=metadata, stdout=driver)
        metadata.seek(0, 0)
        problem_md = json.load(metadata)
        if "judge" in problem_md and problem_md["judge"] == "clonegraph_judge":
            judge_in_duty = judges.clonegraph
        elif "judge" in problem_md and problem_md["judge"] == "wordladders_judge":
            judge_in_duty = judges.wordladders
        else:
            judge_in_duty = judges.general

    #
    # Compose Solution.java
    #
    with open(os.path.join(GENR_PATH, "java.solution.imports"), "r") as sol_imports, \
            open(os.path.join(problem_path, "Solution.java"), "r") as sol_code, \
            open("Solution.java", "w") as final_solution:
        final_solution.write(sol_imports.read())
        final_solution.write(sol_code.read())

    #
    # Compile Driver and Solution, then run solution
    #
    subprocess.call(["javac", "Solution.java"])
    subprocess.call(["javac", "Driver.java"])
    with open(os.path.join(problem_path, "user.in"), "r") as test_data, \
            open(os.path.join(problem_path, "user.out"), "r") as answer:
        sp = subprocess.Popen(["java", "Driver"], stdin=test_data, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        sol_out, sol_err = sp.communicate()
        user_out = sol_out.decode("utf-8")
        user_err = sol_err.decode("utf-8")
        judgement = judge_in_duty(user_out.splitlines(), user_err.splitlines(), answer.read().splitlines())
        if debug:
            with open("user.out", "w") as out_file, open("user.err", "w") as err_file:
                out_file.write(user_out)
                err_file.write(user_err)

    #
    # Print out testing result
    #
    passed = "\033[92m" + "Passed" + "\033[0m"
    failed = "\033[91m" + "Failed" + "\033[0m"
    print(problem_path + " - ", end="")
    print(passed if judgement == "Accepted" else failed)


if "-c" in sys.argv:
    cleanup_gens()
    exit()

if "-d" in sys.argv:
    clear_stage()
    exit()

if "-s" in sys.argv:
    setup_stage()
    exit()

if "-quick" not in sys.argv:
    setup_stage()

if "-all" in sys.argv:
    for directory in os.listdir(P1PROB_PATH):
        if os.path.isdir(os.path.join(P1PROB_PATH, directory)):
            test_problem(os.path.join(P1PROB_PATH, directory), "-debug" in sys.argv)
    for directory in os.listdir(PROB_PATH):
        if os.path.isdir(os.path.join(PROB_PATH, directory)):
            test_problem(os.path.join(PROB_PATH, directory), "-debug" in sys.argv)
else:
    path_list = [p for p in sys.argv[1:] if p not in ["-all", "-quick", "-debug"]]
    if len(path_list) == 0:
        for directory in os.listdir(P1PROB_PATH):
            if os.path.isdir(os.path.join(P1PROB_PATH, directory)):
                test_problem(os.path.join(P1PROB_PATH, directory), "-debug" in sys.argv)
    else:
        for path in path_list:
            test_problem(path, "-debug" in sys.argv)

if "-debug" not in sys.argv:
    cleanup_gens()
exit()
