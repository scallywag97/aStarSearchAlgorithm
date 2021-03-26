package com.company;

import java.util.*;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Node> traversableNodes = new ArrayList<>();
    private static ArrayList<Node> nonTraversableNodes = new ArrayList<>();
    private static ArrayList<Node> openList = new ArrayList<>();
    private static ArrayList<Node> closedList = new ArrayList<>();
    private static Node[][] map = new Node[15][15];
    private static int xCoordinateStartNode = 0, yCoordinateStartNode = 0, xCoordinateGoalNode = 0, yCoordinateGoalNode = 0;

    public static void main(String[] args) {


        generateNewMap(map);


        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (map[i][j].getType() == 1) {
                    nonTraversableNodes.add(map[i][j]);
                } else {
                    traversableNodes.add(map[i][j]);
                }
            }
        }

        System.out.println("Enter x-coordinate for START NODE...(0 - 14)");
        xCoordinateStartNode = input.nextInt();
        System.out.println("Enter y-coordinate for START NODE...(0 - 14)");
        yCoordinateStartNode = input.nextInt();
        System.out.println("Enter x-coordinate for GOAL NODE...(0 - 14)");
        xCoordinateGoalNode = input.nextInt();
        System.out.println("Enter y-coordinate for GOAL NODE...(0 - 14)");
        yCoordinateGoalNode = input.nextInt();

        Node startNode = map[xCoordinateStartNode][yCoordinateStartNode];
        Node goalNode = map[xCoordinateGoalNode][yCoordinateGoalNode];
        Node currentNode = startNode;
        startNode.setG(0);
        startNode.setH(Math.abs(startNode.getCol() - goalNode.getCol()) + Math.abs(startNode.getRow() - goalNode.getRow()));
        startNode.setF();

        openList.add(startNode);

        while (!openList.isEmpty()) {

            currentNode = openList.stream().min(Comparator.comparingInt(Node::getF)).get();
            Node neighborUp = generateUpNeighbor(currentNode);
            Node neighborDown = generateDownNeighbor(currentNode);
            Node neighborLeft = generateLeftNeighbor(currentNode);
            Node neighborRight = generateRightNeighbor(currentNode);

            openList.remove(currentNode);

        }
    }

    public static Node[][] generateNewMap(Node[][] map) {
        int obstacleCount = 0;

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (obstacleCount < 10)
                    map[i][j] = new Node(i, j, 0);
            }
        }

        for (int i = 0; i < 23; i++) {
            while (true) {
                int[] numbers = generateRandomNumbers();
                int num1 = numbers[0];
                int num2 = numbers[1];
                if (map[num1][num2].getType() == 0) {
                    map[num1][num2].setType(1);
                    break;
                }
            }
        }
        return map;
    }

    public static int[] generateRandomNumbers() {
        Random rand = new Random();
        int randomNumbers[] = {rand.nextInt(15), rand.nextInt(15)};
        return randomNumbers;
    }

    public static void print2D(Node[][] map) {
        for (Node[] row : map)
            System.out.println(Arrays.toString(row));
    }

    public static Node generateUpNeighbor(Node node) {
        if(node.getRow() < 0) {
            return null;
        } else {
            node = map[node.getCol()][node.getRow() - 1];
            if(node.getType() == 0 && !closedList.contains(node)) {
                return node;
            } else {
                return null;
            }
        }
    }

    public static Node generateDownNeighbor(Node node) {
        if(node.getRow() < 14) {
            return null;
        } else {
            node = map[node.getCol()][node.getRow() + 1];
            if(node.getType() == 0 && !closedList.contains(node)) {
                return node;
            } else {
                return null;
            }
        }
    }

    public static Node generateLeftNeighbor(Node node) {
        if(node.getCol() > 0) {
            return null;
        } else {
            node = map[node.getCol() - 1][node.getRow()];
            if(node.getType() == 0 && !closedList.contains(node)) {
                return node;
            } else {
                return null;
            }
        }
    }

    public static Node generateRightNeighbor(Node node) {
        if(node.getCol() < 14) {
            return null;
        } else {
            node = map[node.getCol() + 1][node.getRow()];
            if(node.getType() == 0 && !closedList.contains(node)) {
                return node;
            } else {
                return null;
            }
        }
    }
}
