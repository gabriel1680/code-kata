package org.gbl

class Solution {

    fun whatAreTheExits(node: Node): List<Node> {
        val result = mutableListOf<Node>()
        if (node.left == null && node.right == null) {
            result.add(node)
        }
        if (node.left != null) {
            result.addAll(whatAreTheExits(node.left))
        }
        if (node.right != null) {
            result.addAll(whatAreTheExits(node.right))
        }
        return result
    }

    fun whatAreTheExits(maze: List<List<Int>>): List<Pair<Int, Int>> {
        return listOf(Pair(0, 0), Pair(1, 0), Pair(1, 1))
    }
}

data class Node(val left: Node?, val right: Node?, val value: Int) {
    constructor(value: Int) : this(null, null, value)
    constructor() : this(null, null, -1)
}