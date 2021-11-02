package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.Iterator;
public class LinkedListDeque<Item> implements Iterable<Item>, Deque<Item> {

    private Circle sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Circle("sentinel", 0.0, 0.0, 0.0);
        sentinel.setRight(sentinel);
        sentinel.setLeft(sentinel);
        size = 0;
    }

    /**
     * Adds an item of type Item to the back of the deque.
     * Assumption: item is never null.
     */
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("you can't add null!");
        if (!(item instanceof Shape)) throw new IllegalArgumentException("you can't add a non-Shape");
        Shape tmp = (Shape) item;
        sentinel.getLeft().setRight(tmp);
        sentinel.getLeft().getRight().setLeft(sentinel.getLeft());
        sentinel.setLeft(sentinel.getLeft().getRight());
        sentinel.getLeft().setRight(sentinel);
        size++;
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

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * case of "no such item":
     * 1. index out of bounds
     * 2. empty list
     */
    @Override
    public Item get(int index) {
        if (isEmpty()) return null;
        Shape p = sentinel.getRight();
        for (int i = 0; i < size; i++) {
            // check whether get the xth item
            if (index == i) { // within the bounds.
                return (Item) p;
            }
            // move to the next LinkedNode
            p = p.getRight();
        }
        // after iterating through this deque, if index is not in bounds, returns null (= sentinel.item).
        return null;
    }
    public boolean isEmpty() {
        return sentinel.getRight().getName().equals(sentinel.getName());
    }
}
