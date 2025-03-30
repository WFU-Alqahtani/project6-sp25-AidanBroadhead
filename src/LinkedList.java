import java.util.Random;

// linked list class for a deck of cards
public class LinkedList {

    public Node head;
    public Node tail;
    public int size = 0;

    LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    // method to check if list is empty
    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    public void shuffle(int shuffle_count) {

        Random rand = new Random();
        for(int i = 0; i < shuffle_count; i++) {
            // pick two random integers
            int r1 = rand.nextInt(52);
            int r2 = rand.nextInt(52);

            swap(r1,r2); // swap nodes at these indices
        }
    }

    // remove a card from a specific index
    public Card remove_from_index(int index) {
        // FIXME

        if (head == null || index < 0) {
            return null;
        }

        // remove first card and adjust list if index is 0
        if (index == 0) {
            Card removed = head.data;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size = size - 1;
            return removed;
        }

        Node prev = null;
        Node curr = head;
        int currIndex = 0;

        // traverse list
        while (curr != null && currIndex != index) {
            prev = curr;
            curr = curr.next;
            currIndex = currIndex + 1;
        }

        // skip the node at the specified index
        if (curr != null) {
            prev.next = curr.next;
            if (prev.next == null) {
                tail = prev;
            }
            size = size - 1;
            return curr.data;
        }
        return null;

    }

    // insert a card at a specific index
    public void insert_at_index(Card x, int index) {
        // FIXME

        if (index < 0) {
            return;
        }

        // creates new node
        Node addedNode = new Node(x);

        // if index is 0
        if (index == 0) {
            addedNode.next = head;
            head = addedNode;
            // if no tail
            if (tail == null) {
                tail = addedNode;
            }
            size = size + 1;
            return;
        }

        Node prev = null;
        Node curr = head;
        int currIndex = 0;

        // traverse list
        while (curr != null && currIndex != index) {
            prev = curr;
            curr = curr.next;
            currIndex = currIndex + 1;
        }

        // insert new node to list
        if (curr != null) {
            prev.next = addedNode;
            addedNode.next = curr;
            if (curr == tail) {
                tail = addedNode;
            }
            size = size + 1;
        } else if (currIndex == index) {
            prev.next = addedNode;
            tail = addedNode;
            size = size + 1;
        }
    }

    // swap two cards in the deck at the specific indices
    public void swap(int index1, int index2) {
        // FIXME
        if (index1 < 0 || index2 < 0 || index1 == index2) {
            return;
        }

        // finds nodes at each index
        Node prev1 = null;
        Node curr1 = head;
        int i = 0;

        // traverse list until given index
        while (curr1 != null && i < index1) {
            prev1 = curr1;
            curr1 = curr1.next;
            i++;
        }

        Node prev2 = null;
        Node curr2 = head;
        i = 0;

        // traverse list until given index
        while (curr2 != null && i < index2) {
            prev2 = curr2;
            curr2 = curr2.next;
            i++;
        }

        // checks if nodes at each index are null
        if (curr1 == null || curr2 == null) {
            return;
        }

        // remove nodes
        if (prev1 != null) {
            prev1.next = curr2;
        } else {
            head = curr2;
        }

        if (prev2 != null) {
            prev2.next = curr1;
        } else {
            head = curr1;
        }

        Node temp = curr1.next;
        curr1.next = curr2.next;
        curr2.next = temp;

    }

    // add card at the end of the list
    public void add_at_tail(Card data) {
        // FIXME

        // create new node
        Node addedNode = new Node(data);

        // add node to list
        if (head == null) {
            head = addedNode;
            tail = addedNode;
        } else {
            addedNode.prev = tail;
            tail.next = addedNode;
            tail = addedNode;
        }
        size = size + 1;

    }

    // remove a card from the beginning of the list
    public Card remove_from_head() {
        // FIXME

        if (head == null) {
            return null;
        }

        // remove card
        Card removed = head.data;
        head = head.next;
        // update tail
        if (head == null) {
            tail = null;
        }
        size = size - 1;
        return removed;

    }

    // check to make sure the linked list is implemented correctly by iterating forwards and backwards
    // and verifying that the size of the list is the same when counted both ways.
    // 1) if a node is incorrectly removed
    // 2) and head and tail are correctly updated
    // 3) each node's prev and next elements are correctly updated
    public void sanity_check() {
        // count nodes, counting forward
        Node curr = head;
        int count_forward = 0;
        while (curr != null) {
            curr = curr.next;
            count_forward++;
        }

        // count nodes, counting backward
        curr = tail;
        int count_backward = 0;
        while (curr != null) {
            curr = curr.prev;
            count_backward++;
        }

        // check that forward count, backward count, and internal size of the list match
        if (count_backward == count_forward && count_backward == size) {
            System.out.println("Basic sanity Checks passed");
        }
        else {
            // there was an error, here are the stats
            System.out.println("Count forward:  " + count_forward);
            System.out.println("Count backward: " + count_backward);
            System.out.println("Size of LL:     " + size);
            System.out.println("Sanity checks failed");
            System.exit(-1);
        }
    }

    // print the deck
    public void print() {
        Node curr = head;
        int i = 1;
        while(curr != null) {
            curr.data.print_card();
            if(curr.next != null)
                System.out.print(" -->  ");
            else
                System.out.println(" X");

            if (i % 7 == 0) System.out.println("");
            i = i + 1;
            curr = curr.next;
        }
        System.out.println("");
    }
}