package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.Iterator;
public class LinkedListDeque<Item> implements Iterable<Item>, Deque<Item> {

    private Circle sentinel;

    public LinkedListDeque() {
        sentinel = new Circle("sentinel", 0.0, 0.0, 0.0);
        sentinel.setRight(sentinel);
        sentinel.setLeft(sentinel);
    }

    /**
     * Adds an item of type Item to the back of the deque.
     * Assumption: item is never null.
     */
    public void addLast(Item item) {
        Shape tmp = (Shape) item;
        sentinel.getLeft().setRight(tmp);
        sentinel.getLeft().getRight().setLeft(sentinel.getLeft());
        sentinel.setLeft(sentinel.getLeft().getRight());
        sentinel.getLeft().setRight(sentinel);
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
        private Shape scanner = sentinel.getRight();

        @Override
        public boolean hasNext() {
            return (scanner != sentinel);
        }

        @Override
        public Item next() {
            Item returnItem = (Item) scanner;
            scanner = scanner.getRight();
            return returnItem;
        }
    }

    public boolean isEmpty() {
        return sentinel.getRight().getName().equals(sentinel.getName());
    }

}