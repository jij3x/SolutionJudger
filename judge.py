import subprocess
import os
import os.path
import shutil
import evaluate
import pprint
import argparse
from environment import *

parser = argparse.ArgumentParser()
parser.add_argument("lang", metavar="language", type=str, nargs="?", default="ruby", help="programming language")
parser.add_argument("dir", metavar="directory", type=str, nargs="?",
                    default="/Users/jianxioj/SolutionJudger/_designs_001/Add and Search Word - Data structure design/",
                    help="problem directory")
parser.add_argument("-e", "--erase", help="erase the stage", action="store_true")
parser.add_argument("-c", "--clean", help="cleanup generated files", action="store_true")
parser.add_argument("-s", "--setup", help="setup the stage - copy all necessary files, and compile accordingly",
                    action="store_true")
parser.add_argument("-q", "--quick", help="use existing generated files, instead of starting from scratch",
                    action="store_true")
parser.add_argument("-d", "--debug", help="leave all generated files", action="store_true")
args = parser.parse_args()
lang = args.lang

def cleanup_gens():
    keep_files = {".gitignore", "evaluate.py", "judge.py", "test_runner.py"}

    for fname in os.listdir(GENR_PATH["java"]):
        if fname.endswith(".java"):
            keep_files.add(fname)
            keep_files.add(fname.split(".")[0] + ".class")

    for fname in os.listdir(GENR_PATH["ruby"]):
        if fname.endswith(".rb"):
            keep_files.add(fname)

    for fname in os.listdir("."):
        if os.path.isfile(fname) and fname not in keep_files:
            os.remove(fname)


def clear_stage():
    keep_files = {".gitignore", "evaluate.py", "judge.py", "test_runner.py"}

    for fname in os.listdir("."):
        if fname not in keep_files and \
                (fname.endswith(".java") or fname.endswith(".class") or
                     fname.endswith(".rb")):
            os.remove(fname)


def setup_stage(lang):
    clear_stage()

    if lang == "java":
        for fname in os.listdir(GENR_PATH[lang]):
            if fname.endswith(".java"):
                shutil.copy(os.path.join(GENR_PATH[lang], fname), ".")
        for fname in os.listdir("."):
            if fname.endswith(".java"):
                subprocess.call(["javac", fname])
    elif lang == "ruby":
        for fname in os.listdir(GENR_PATH[lang]):
            if fname.endswith(".rb"):
                shutil.copy(os.path.join(GENR_PATH[lang], fname), ".")


if args.clean:
    cleanup_gens()
    exit()

if args.erase:
    clear_stage()
    exit()

if args.setup:
    setup_stage(lang)
    exit()

if not args.quick:
    setup_stage(lang)

pprint.pprint(evaluate.eval_sol[lang](args.dir))

if not args.debug:
    cleanup_gens()
