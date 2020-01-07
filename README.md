# RubiksCubeSolver
A Rubik's cube solver written in Java that finds a near-optimal solution that is guaranteed to have <=20 moves. 

The search algorithm used is bi-directional A* with a heurisitic function that finds the optimal solution of the 2x2x2 Rubik's cube made up of the corner cubies of the current cube. This value is used to determine which branch to explore first. The search depth of A* is set to 10 on either side, so a solution in  <=20 moves is guaranteed.
