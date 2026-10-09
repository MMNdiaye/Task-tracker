# Task Tracker CLI

This simple Task Tracker application uses a command line interface to help you track and manage your tasks.
This application is a project taken from [roadmap.sh](https://roadmap.sh/projects/task-tracker) and the requirements
come from them as well. 

## Requirements 
Your data is stored in a json file.
No external library has been used, so the java-native I/O operations and a custom JsonParser has been used.

## Commands
- Add a task: **add** taskName _{taskDescription}_
- List all tasks: **list**
- List tasks filtered by status: **list** {todo|done|in-progress}
- Update tasks name: **update** taskId newName
- Mark tasks as in progress: **mark-in-progress** taskId
- Mark tasks as done: **mark-done** taskId
- Delete task: **delete** taskId
- Exit application: **exit**

Note that values of one word for string attributes like newName or taskName don't need
to be enclosed by quotes. For multiple words strings you enclose them inside quotes so
the command parser doesn't interpret the spaces inside quotes.
Examples:
- add newTask
- add "newTask with spaces"

If you want to use the quote character inside your string you must escape it with *'\'*
so it doesn't close your string.  
Example: add newTask "In this \\\"description\\\" there are quotes"


