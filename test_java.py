import subprocess
import sys
import os
import filecmp

test_dir = sys.argv[1]
pythoncmd = "python"


def clean_up_currdir():
    for file in os.listdir("."):
        if file.endswith(".class") or file == "Driver.java" or file == "Solution.java" or file == "user.out":
            os.remove(file)


clean_up_currdir()

#
# Find problem metadata
#
json_file = ""
for file in os.listdir(test_dir):
    if file.endswith(".json"):
        json_file = "{}/{}".format(test_dir, file)
        break

#
# Generate Driver.java
#
with open("Driver.java", "w") as driver:
    subprocess.call([pythoncmd, "gen_java_driver.py", json_file], stdout=driver)

#
# Compose Solution.java
#
solution_fname = "{}/{}".format(test_dir, "Solution.java")
with open(solution_fname) as solution_file:
    solution_src = solution_file.read() + "\n"
with open("java.solution.imports") as sol_imports:
    solution_src = sol_imports.read() + solution_src
with open("Solution.java", "w") as final_solution:
    final_solution.write(solution_src)

#
# Compile all java classes, and run solution
#
subprocess.call(["javac", "*.java"])
with open("{}/{}".format(test_dir, "user.new.in")) as test_data, open("user.out", "w") as result:
    subprocess.call(["java", "Driver"], stdin=test_data, stdout=result)

#
# Print out testing result
#
print(test_dir + " - ", end="")
print("passed" if filecmp.cmp("user.out", "{}/{}".format(test_dir, "user.new.out")) else "failed!!!")

clean_up_currdir()
