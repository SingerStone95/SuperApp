package com.singerstone.jojo

class 二叉树还原2 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inOrder = arrayOf(9, 3, 15, 20, 7)
            val preOrder = arrayOf(3, 9, 20, 15, 7)
            二叉树构建.visitTree(二叉树还原2.buildTree(preOrder.toIntArray(), inOrder.toIntArray()))

        }

        //preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
            if (inorder.size == 0) {
                return null;
            }
            if (inorder.size == 1) {
                return TreeNode(preorder[0]);

            }
            val node = TreeNode(preorder[0])
            var index = 0;
            for (i in inorder.indices) {
                if (inorder[i] == preorder[0]) {
                    index = i;
                    break;
                }
            }

            val sub_inorder_left = inorder.copyOfRange(0, index);
            if (sub_inorder_left.size == 0) {
                node.left = null;
            } else {
                node.left = buildTree(preorder.copyOfRange(1, 1 + sub_inorder_left.size), sub_inorder_left);
            }
            val sub_inorder_right = inorder.copyOfRange(index + 1, preorder.size);
            if (sub_inorder_right.size == 0) {
                node.right = null;
            } else {
                node.right = buildTree(preorder.copyOfRange(1 + sub_inorder_left.size, preorder.size), sub_inorder_right);
            }
            return node;

        }

    }
}