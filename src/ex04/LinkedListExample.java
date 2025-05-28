package ex04;

class Node {
    int data;
    Node next;

    Node(int data){
        this.data = data;
        this.next = null;
    }
}

class MyLinkedList {
    Node head;

    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode; //리스트가 비어 있으면 head로 설정
        }else{
            Node current = head;
            while(current.next != null) {
                current = current.next;
            }
            current.next = newNode; //마지막 Node의 next에 새 Node 시작
        }
    }

    public void printList(){
        Node current = head;
        while(current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}



public class LinkedListExample {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);

        list.printList();
    }
}
