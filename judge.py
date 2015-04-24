"""
-d Clear the stage. All java related files will be deleted.
-c Cleanup generated files (Driver, Solution). All other java files and class files will stay.
-s Reset the stage. Prepare all java files, and compile them.

-quick Use existing compiled class, instead of starting from scratch.
-debug Leave Driver, and, Solution.
"""

import subprocess
import sys
import os
import os.path
import shutil
import evaluate
import pprint

PYCMD = "python"
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

problems = [p for p in sys.argv[1:] if p not in ["-all", "-quick", "-debug"]]
for problem_path in problems:
    pprint.pprint(evaluate.eval_java_sol(problem_path))

if "-debug" not in sys.argv:
    cleanup_gens()
