import subprocess
import sys
import os
import os.path
import evaluate as ev

PYCMD = "python"
GENR_PATH = "java_generator"
JUDGE = "judge.py"


class Runner:
    def __init__(self):
        self.total = 0
        self.failed = 0

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
                result = ev.eval_java_sol(problem_path)

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


subprocess.call([PYCMD, JUDGE, "-d"])
subprocess.call([PYCMD, JUDGE, "-s"])

runner = Runner()
for path in sys.argv[1:]:
    runner.evaluate(path)
runner.print_summary()

subprocess.call([PYCMD, JUDGE, "-d"])
