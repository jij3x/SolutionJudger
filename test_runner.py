import subprocess
import os
import os.path
import evaluate as ev
from environment import *
import argparse

parser = argparse.ArgumentParser()
parser.add_argument("lang", metavar="language", type=str, nargs="?", default="ruby", help="programming language")
parser.add_argument("dir", metavar="directory", type=str, nargs="*", default=["."], help="problem directory")
args = parser.parse_args()
lang = args.lang


class Runner:
    def __init__(self, lang):
        self.total = 0
        self.failed = 0
        self.lang = lang

    def evaluate(self, problem_path):
        filenames = os.listdir(problem_path)
        contains_json = False
        contains_userin = False
        for filename in filenames:
            if os.path.isfile(os.path.join(problem_path, filename)):
                if filename.endswith(".json"):
                    contains_json = True
                if filename == "user.in":
                    contains_userin = True

            if contains_json and contains_userin:
                result = ev.eval_sol[self.lang](problem_path)

                passed = "\033[92m" + "Passed" + "\033[0m"
                failed = "\033[91m" + "Failed" + "\033[0m"
                print("%s - runtime: %.5fms %s" %
                      (problem_path, result[ev.R_EXECTIME], passed if result[ev.R_RC] == ev.AC else failed))
                self.total += 1
                self.failed += 1 if result[ev.R_RC] != ev.AC else 0
                return

        for directory in filenames:
            if os.path.isdir(os.path.join(problem_path, directory)):
                self.evaluate(os.path.join(problem_path, directory))

    def print_summary(self):
        print("\nTotal {} problems, ".format(self.total), end="")
        print("all passed" if self.failed == 0 else "{} failed".format(self.failed))


subprocess.call([PYCMD, "judge.py", "-e"])
subprocess.call([PYCMD, "judge.py", lang, "-s"])

runner = Runner(lang)
for path in args.dir:
    runner.evaluate(path)
runner.print_summary()

subprocess.call([PYCMD, "judge.py", "-e"])
