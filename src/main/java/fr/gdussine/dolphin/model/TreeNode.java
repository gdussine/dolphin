package fr.gdussine.dolphin.model;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TreeNode<T> implements Iterable<TreeNode<T>> {

    T data;
    TreeNode<T> parent;
    List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new LinkedList<TreeNode<T>>();
    }

    public TreeNode<T> addChild(T child) {
        TreeNode<T> childNode = new TreeNode<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    @NotNull
    @Override
    public Iterator<TreeNode<T>> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super TreeNode<T>> action) {

    }

    @Override
    public Spliterator<TreeNode<T>> spliterator() {
        return null;
    }


}
