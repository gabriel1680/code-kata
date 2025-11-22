import org.assertj.core.api.Assertions.assertThat
import org.gbl.Node
import org.gbl.Solution
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SolutionTest {

    private val sut = Solution()

    @Nested
    inner class BinaryTree {

        @Test
        fun oneExit() {
            assertEquals(listOf(Node()), sut.whatAreTheExits(Node()))
        }

        @Test
        fun oneExitOnLeft() {
            val exit = Node(2)
            val tree = Node(exit, null, 1)
            assertThat(sut.whatAreTheExits(tree)).contains(exit)
        }

        @Test
        fun oneExitOnRight() {
            val exit = Node(2)
            val tree = Node(null, exit, 1)
            assertThat(sut.whatAreTheExits(tree)).contains(exit)
        }

        @Test
        fun twoExits() {
            val exit1 = Node(2)
            val exit2 = Node(3)
            val tree = Node(exit1, exit2, 1)
            assertThat(sut.whatAreTheExits(tree)).contains(exit1, exit2)
        }

        @Test
        fun threeExits() {
            val exit3 = Node(5)
            val exit2 = Node(4)
            val path1 = Node(exit2, exit3, 2)
            val exit1 = Node(3)
            val tree = Node(path1, exit1, 1)
            assertThat(sut.whatAreTheExits(tree)).contains(exit1, exit2, exit3)
        }

        @Test
        fun manyExits() {
            val exit4 = Node(5)
            val exit3 = Node(4)
            val path1 = Node(exit3, exit4, 2)
            val exit1 = Node(3)
            val exit2 = Node(3)
            val path2 = Node(exit1, exit2, 2)
            val tree = Node(path1, path2, 1)
            assertThat(sut.whatAreTheExits(tree)).contains(exit1, exit2, exit3, exit4)
        }
    }

    @Nested
    inner class Maze {

        @Test
        fun A2x2Maze() {
            var maze = listOf(listOf(1, 0), listOf(1, 2))
            assertThat(sut.whatAreTheExits(maze)).contains(Pair(0, 0), Pair(1, 0), Pair(1, 1))

            maze = listOf(listOf(1, 1), listOf(0, 2))
            assertThat(sut.whatAreTheExits(maze)).contains(Pair(0, 0), Pair(1, 0), Pair(1, 1))
        }
    }
}
