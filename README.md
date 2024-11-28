# MatrixView

## Project information
**By Mark Moutter, Ahmet Yusuf Yildirim, Le Tuan Huy Nguyen**  

MatrixView is the final team project for *Program development in a graphical environment, 420-203*
Vanier College, Fall 2024 Semester.

**README Updated:** November 28, 2024, by Ahmet Yusuf Yildirim.

## Features

### 1. **Export Button**  
- This button allows the user to export the current visual representation of the inputted matrix.
- When exporting, the user has the to option to decide where he/she wants to export the image.
- Each image will have as a default export name, the user inputted matrix.
- The image also contains the calculated values of the matrix, such as the determinant, the inverse and the transpose.

### 2. **Showing the Initial Grid**  
- This checkbox field allows the user to compare the inputted matrix with the base matrix that we are all familiar with.
- This base matrix represents the x and y lines in a graph.

### 3. **The Main Area**  
- This part of the application contains the cartesian plane that draws the shift in the space.

### 4. **Resizability**  
- The application has a responsive interface.
- When the application window is resized, the canvas and the other UI components also resize to accommodate for the user action.

### 5. **Zooming**  
- The user can either zoom in or zoom out towards the matrix, either to see a more general view of the transformation in the space of to see a more detailed
     transformation.
- To use this feature, the user can use the wheel of their mouse, or the dedicated slider in *Simulation configuration*. 

### 6. **User Interaction**  
- Allow the user to enter their own two by two matrix values. 
- The spinners will allow the users to increment the current value by 0.5.
- The spinners have a range of -100 to 100.

### 7. **Calculated Values**  
- This section presents the calculated values of determinant, the inverse, and the transpose values of the matrix.
- This section also contains a button that allows the user to visualize the inverse of the inputted matrix.
- This section also contains *DetInfo*, which implements the row reduction method to say whether the matrix is invertible.

### 8. **Animation Control**  
- This section allows the user to start or reset the simulation. 

## How do I use MatrixView?

Building MatrixView requires the end user to have a functioning version of IntelliJ IDEA or NetBeans with Gradle capabilities.

1. Download/Clone the project: [MatrixView GitHub Repository](https://github.com/ComradeMark/matrix-view).
2. If the user choice of IDE is IntelliJ, after opening the project, the user must go to **Run** → **Edit Configurations**. 
3. Add a new Gradle configuration to the project.
4. Add the *run* condition in the *Run* command input field.
5. Run the project.

## Information on project commits and realization

### 1. **Project Commits**  
#### 1. **Frequency of Commits**  
- Weekly.   

#### 2. **Content of Commits**  
- Bug fixes, feature development, refactoring, styling, and code cleaning.     
   
#### 3. **Commit Messages**  
- Mixed in quality, sometimes lacking seriousness and maturity.  
- Occasionally not descriptive enough.  
   
#### 4. **Branching Strategy**  
- Since it was a relatively simple project, we decided to go ahead with the single branch. 
- We later regretted that decision, due to a couple big git conflicts.

#### 5. **Collaborative Practices**  
- Each team member focused on their assigned tasks.  
- Any desire to work on unassigned features was communicated in advance.

### 2. **Project Realization**  
#### 1. **Progress Milestones**  
- Achievements align with the original timeline.  
- The project was completed ahead of schedule with time to spare.  
- All initially planned features were implemented.  

#### 2. **Build Stages**  
- The project implementation was divided into three builds.

#### 3. **Challenges Faced**  
- Lack of prior application development experience.  
- Smaller issues related to project implementation and git conflicts.  
- Challenges were addressed through teacher guidance and online research.
   
#### 4. **Current Stage**  
- The project has currently been finished, and is ready for deployment.
   
#### 5. **Team Contributions**  
Each team member contributed to every part of the project, but specific responsibilities included:  
- **Tony**: Animation implementation.  
- **Mark**: Connecting UI elements for value retrieval and assignment.  
- **Ahmet**: Export functionality and application styling.
