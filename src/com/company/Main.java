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
        Node currentNode;
        startNode.setG(0);
        startNode.setH(Math.abs(startNode.getCol() - goalNode.getCol()) + Math.abs(startNode.getRow() - goalNode.getRow()));
        startNode.setF();

        openList.add(startNode);

        while (!openList.isEmpty()) {

            currentNode = openList.stream().min(Comparator.comparingInt(Node::getF)).get();
            Node temp;
            Node neighborUp = generateUpNeighbor(currentNode);
            if(neighborUp != null) {
                temp = neighborUp;
                temp.setG(currentNode.getG() + 10);
                temp.setH(Math.abs(neighborUp.getCol() - neighborUp.getCol()) + Math.abs(neighborUp.getRow() - neighborUp.getRow()));
                temp.setF();
                if(openList.contains(neighborUp) && openList.get(openList.indexOf(neighborUp)).getF() < temp.getF()) {

                } else if(closedList.contains(neighborUp)) {

                } else {
                    neighborUp.setG(currentNode.getG() + 10);
                    neighborUp.setH(Math.abs(neighborUp.getCol() - neighborUp.getCol()) + Math.abs(neighborUp.getRow() - neighborUp.getRow()));
                    neighborUp.setF();
                    neighborUp.setParent(currentNode);
                    openList.add(neighborUp);
                }
            }

            Node neighborDown = generateDownNeighbor(currentNode);
            if(neighborDown != null) {
                temp = neighborDown;
                temp.setG(currentNode.getG() + 10);
                temp.setH(Math.abs(neighborDown.getCol() - neighborDown.getCol()) + Math.abs(neighborDown.getRow() - neighborDown.getRow()));
                temp.setF();
                if(openList.contains(neighborDown) && openList.get(openList.indexOf(neighborDown)).getF() < temp.getF()) {

                } else if(closedList.contains(neighborDown)) {

                } else {
                    neighborDown.setG(currentNode.getG() + 10);
                    neighborDown.setH(Math.abs(neighborDown.getCol() - neighborDown.getCol()) + Math.abs(neighborDown.getRow() - neighborDown.getRow()));
                    neighborDown.setF();
                    neighborDown.setParent(currentNode);
                    openList.add(neighborDown);
                }
            }
            Node neighborLeft = generateLeftNeighbor(currentNode);
            if(neighborLeft != null) {
                temp = neighborLeft;
                temp.setG(currentNode.getG() + 10);
                temp.setH(Math.abs(neighborLeft.getCol() - neighborLeft.getCol()) + Math.abs(neighborLeft.getRow() - neighborLeft.getRow()));
                temp.setF();
                if(openList.contains(neighborLeft) && openList.get(openList.indexOf(neighborLeft)).getF() < temp.getF()) {

                } else if(closedList.contains(neighborLeft)) {

                } else {
                    neighborLeft.setG(currentNode.getG() + 10);
                    neighborLeft.setH(Math.abs(neighborUp.getCol() - neighborUp.getCol()) + Math.abs(neighborUp.getRow() - neighborUp.getRow()));
                    neighborLeft.setF();
                    neighborLeft.setParent(currentNode);
                    openList.add(neighborLeft);
                }
            }
            Node neighborRight = generateRightNeighbor(currentNode);
            if(neighborRight != null) {
                temp = neighborRight;
                temp.setG(currentNode.getG() + 10);
                temp.setH(Math.abs(neighborRight.getCol() - neighborRight.getCol()) + Math.abs(neighborRight.getRow() - neighborRight.getRow()));
                temp.setF();
                if(openList.contains(neighborRight) && openList.get(openList.indexOf(neighborRight)).getF() < temp.getF()) {

                } else if(closedList.contains(neighborRight)) {

                } else {
                    neighborRight.setG(currentNode.getG() + 10);
                    neighborRight.setH(Math.abs(neighborUp.getCol() - neighborUp.getCol()) + Math.abs(neighborUp.getRow() - neighborUp.getRow()));
                    neighborRight.setF();
                    neighborRight.setParent(currentNode);
                    openList.add(neighborRight);
                }
            }
            openList.remove(currentNode);
            closedList.add(currentNode);
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
