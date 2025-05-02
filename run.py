import subprocess
import sys

def compileAndRun(args):

    # Use the subprocess module to run the 'javac' command for compiling 'MyInfArith.java'
    # capture_output=True stores both stdout and stderr, and text=True returns output as strings
    compile = subprocess.run(["javac", "MyInfArith.java"], capture_output=True, text=True )

    # If the compilation is unsucessful, it prints the error of the compilation which is stored in stderr
    if( compile.returncode != 0 ):
        print("Compilation Failed")
        print(compile.stderr)

    else:

        # Use the subprocess module to run the compiled Java program 'MyInfArith.java' with command-line arguments
        # capture_output=True captures the program's output, and text=True ensures it is returned as a string
        run = subprocess.run(["java", "MyInfArith.java", args[0], args[1], args[2], args[3]], capture_output=True, text=True)

        # Print the standard output from the Java program stored in stdout without adding an extra newline
        print(run.stdout , end = "")


if __name__ == "__main__":
    compileAndRun(sys.argv[1:])
