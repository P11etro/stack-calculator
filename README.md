# SCIENTIFIC COMPLEX CALCULATOR
A simple stack-based calculator that supports complex numbers.

<img width="760" alt="calculator" src="https://github.com/GiaSen/group11_software_eng/assets/148695294/c130369b-2745-413c-ba3c-eec17419c4cf">

### Features
The calculator implements:
- Arithmetic operations (+,-,/,*,+-,squareroot).
- Stack operations (swap,over,dup,drop,clear).
- Variable operations (fromVarToStack,fromStackToVar,sumVar,subVar).

### Numbers
The valid format for complex number insertion is a+bj.  
a and b are double type numbers.
- The real number -4.21 is a valid input.
- The imaginary number 0.43j is a valid input.
- The complex number 3.2j+3 is not a valid input.

### Stack
The possible stack operations are:
- Swap: the two top elements are switched by position.
- Over: a copy of the second last elements is pushed to the top.
- Dup: a copy of the last element is pushed to the top.
- Drop: the last element is removed.
- Clear: all the elements are removed from the stack.

### Variables
The possible variable operations are:
- fromVarToStack (<var): insert the current value of the variable in the text box.
- fromStackToVar (>var): insert the current value of the text box in the variable.
- sumVar (+var): sum the current value in the text box to the variable.
- subVar (-var): sub the current value in the text box to the variable.

var can be any letter from 'a' to 'z'.  
The current value of each variable can be viewed in the dedicated combo-box.

##### This calculator is a group project for the Software Engineering exam at the University of Salerno.
### Group Members
#### PANTANI Pietro
#### ROBERTO Simone
#### RUSSO Vincenzo Pietro
#### SENATORE Giammarco
