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
import filecmp
import shutil

PYCMD = "python3"
P1PROB_PATH = "_p1_problems"
PROB_PATH = "_problems"
GENR_PATH = "JavaGenerator"


def cleanup_gens():
    for fname in os.listdir("."):
        if fname in ("Driver.java", "Solution.java", "Driver.class", "Solution.class", "user.out"):
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


def test_problem(problem_path):
    #
    # Find problem metadata
    #
    metadata_fname = ""
    for file in os.listdir(problem_path):
        if file.endswith(".json"):
            metadata_fname = os.path.join(problem_path, file)
            break

    #
    # Generate Driver.java
    #
    with open("Driver.java", "w") as driver, open(metadata_fname, "r") as metadata:
        subprocess.call([PYCMD, os.path.join(GENR_PATH, "gen_java_driver.py")], stdin=metadata, stdout=driver)

    #
    # Compose Solution.java
    #
    with open(os.path.join(problem_path, "Solution.java")) as solution_file:
        solution_src = solution_file.read() + "\n"
    with open(os.path.join(GENR_PATH, "java.solution.imports")) as sol_imports:
        solution_src = sol_imports.read() + solution_src
    with open("Solution.java", "w") as final_solution:
        final_solution.write(solution_src)

    #
    # Compile Driver and Solution, then run solution
    #
    subprocess.call(["javac", "Solution.java"])
    subprocess.call(["javac", "Driver.java"])
    with open(os.path.join(problem_path, "user.in")) as test_data, open("user.out", "w") as result:
        subprocess.call(["java", "Driver"], stdin=test_data, stdout=result)

    #
    # Print out testing result
    #
    passed = "\033[92m" + "Passed" + "\033[0m"
    failed = "\033[91m" + "Failed" + "\033[0m"
    print(problem_path + " - ", end="")
    print(passed if filecmp.cmp("user.out", os.path.join(problem_path, "user.out")) else failed)


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
    for dir in os.listdir(P1PROB_PATH):
        if os.path.isdir(os.path.join(P1PROB_PATH, dir)):
            test_problem(os.path.join(P1PROB_PATH, dir))
    for dir in os.listdir(PROB_PATH):
        if os.path.isdir(os.path.join(PROB_PATH, dir)):
            test_problem(os.path.join(PROB_PATH, dir))
else:
    path_list = [p for p in sys.argv[1:] if p not in ["-all", "-quick", "-debug"]]
    if len(path_list) == 0:
        for dir in os.listdir(P1PROB_PATH):
            if os.path.isdir(os.path.join(P1PROB_PATH, dir)):
                test_problem(os.path.join(P1PROB_PATH, dir))
    else:
        for problem_path in path_list:
            test_problem(problem_path)

if "-debug" not in sys.argv:
    cleanup_gens()
exit()
