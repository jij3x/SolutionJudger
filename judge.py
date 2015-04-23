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
import evaluate as ev
import pprint

PYCMD = "python"
P1PROB_PATH = "_p1_problems"
PROB_PATH = "_problems"
GENR_PATH = "JavaGenerator"


def cleanup_gens():
    for fname in os.listdir("."):
        if fname in ("Driver.java", "Solution.java", "Driver.class", "Solution.class",
                     "user.out", "user.out.unfiltered", "user.err"):
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

total_problem = 0
total_failed = 0
debug = "-debug" in sys.argv


def run(problem_path):
    global total_problem
    global total_failed
    global debug

    total_problem += 1
    result = ev.eval_java_sol(problem_path)

    passed = "\033[92m" + "Passed" + "\033[0m"
    failed = "\033[91m" + "Failed" + "\033[0m"

    print(problem_path + " - ", end="")
    if result[ev.R_RC] == ev.RC_AC:
        print("runtime: %.5fms %s" % (result[ev.R_ET], passed))
    else:
        print("runtime: %.5fms %s" % (result[ev.R_ET], failed))
        total_failed += 1

    if debug:
        with open("user.out", "w") as userout_file:
            pprint.pprint(result, userout_file)


if "-all" in sys.argv:
    for directory in os.listdir(P1PROB_PATH):
        if os.path.isdir(os.path.join(P1PROB_PATH, directory)):
            run(os.path.join(P1PROB_PATH, directory))
    for directory in os.listdir(PROB_PATH):
        if os.path.isdir(os.path.join(PROB_PATH, directory)):
            run(os.path.join(PROB_PATH, directory))
else:
    path_list = [p for p in sys.argv[1:] if p not in ["-all", "-quick", "-debug"]]
    if len(path_list) == 0:
        for directory in os.listdir(P1PROB_PATH):
            if os.path.isdir(os.path.join(P1PROB_PATH, directory)):
                run(os.path.join(P1PROB_PATH, directory))
    else:
        for path in path_list:
            run(path)

print("\nTotal {} problems, ".format(total_problem), end="")
print("all passed" if total_failed == 0 else "{} failed".format(total_failed))

if not debug:
    cleanup_gens()
