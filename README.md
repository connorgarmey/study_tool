## Program Functions
The app has two modes, a Study Guide Creator and a Spaced Repetition Study Session which are activated by providing the specified command-line arguments

# The Study Guide Creator takes three (3) command-line arguments:

1. A relative or absolute path to a folder (directory) of markdown files containing the notes you want to summarize.  We will call this folder **notes-root**. 
    4. Example paths:
        1. `/Users/jim-john/Documents/notes/cs3500` - an absolute path
        2. `my-notes/cs/class4`- a relative path
2. Ordering Flag - A flag to indicate how the summary document should be organized
    1. `filename` - organize the content in the output summary file in order based on the alphabetically sorted source file names. 
    2. `created` - organize the content in the output summary file in order based on the create-date time stamp of the source file. 
    3. `modified` - organize the content in the output summary file in order based on the the last modified time stamp of the source file. 
3. An output path (relative or absolute) and filename of where to write the study guide your program generates. Based on the input file processing order dictated by command-line argument #2 above, the output file will contain:
    1. all headings in the order they appear in the file (properly nested). 
        1. Except for the very first line of the study guide file, all headings should be preceded with a blank line. 
    2. all important phrases identified with the `[[]]` properly nested under the heading in which it appears in the original input file. 
        1. In the output file, do not output the brackets themselves. 
        2. Each bracketed phrase should be output as a single bullet point (`-`) in the output file. 
        3. Bracketed phrases or sentences may span multiple lines of the input file. 
        4. A single line of the input file may contain multiple bracketed important phrases; each should be output as a separate bullet point in the study guide.

# The Spaced Repetition study session progresses like this:

1. The user provides a path to a `.sr` file that contains a set of questions and their associated metadata. We’ll call this the “question bank”. 
2. The user indicates how many questions they would like to be presented in this study session. If the total number of questions in the question bank is smaller than the total number of questions the user wants to practice, they will be quizzed on all the questions in the file. 
3. The app selects a set of questions from the question bank file for this study session.  The app will first show all questions labeled as **hard** in random order.  Then, questions currently labeled as easy will be shown in random order. In other words, the hard questions will randomly be shown first followed by a random selection of easy questions.  
4. Once the questions are selected, each question is shown to the user.  The user will be able to mark each question as hard or easy, meaning that it was hard to think of the answer (or the user couldn’t actually think of it) or thinking of the answer was easy.  The model is updated to reflect choices made by the user. 
5. At the end of the study session, the app shows the user some stats including:
    1. the total number of questions answered for that session, 
    2. the total number of questions that changed from easy to hard, 
    3. the total number of questions that changed from hard to easy, 
    4. the updated total number of hard questions in the question bank, and
    5. the updated total number of easy questions in the question bank.
