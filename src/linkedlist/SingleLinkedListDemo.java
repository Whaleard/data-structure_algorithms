package linkedlist;

/**
 * @author Mr.MC
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 添加
        // singleLinkedList.add(hero1);
        // singleLinkedList.add(hero2);
        // singleLinkedList.add(hero3);
        // singleLinkedList.add(hero4);

        // 按照编号的顺序添加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        // 显示
        singleLinkedList.list();

        // 测试修改节点
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);

        System.out.println("修改后的链表情况");
        singleLinkedList.list();

        // 删除节点
        singleLinkedList.delete(1);
        System.out.println("删除后的链表情况");
        singleLinkedList.list();

        /**
         * 测试：获取单链表的有效节点个数
         */
        System.out.println("有效的节点个数：" + getLength(singleLinkedList.getHead()));
    }

    /**
     * 获取单链表的节点个数（如果带头节点，不统计头节点）
     *
     * @param head 链表的头节点
     * @return 返回有效节点的个数
     */
    public static int getLength(HeroNode head) {
        // 判断链表是否为空
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        // 定义一个辅助变量
        HeroNode temp = head.next;
        // 遍历
        while (temp != null) {
            temp = temp.next;
            length++;
        }
        return length;
    }

}

/**
 * 定义SingleLinkedList管理HeroNode节点
 */
class SingleLinkedList {
    /**
     * 先初始化一个头节点（头节点不能变动，一旦变动就找不到该链表），头节点不存放具体数据
     */
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 返回头节点
     * @return
     */
    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表（不考虑编号顺序）
     *
     * 思路：
     *  1、找到当前链表的最后节点
     *  2、将最后节点的next指向新的节点
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        // 由于head节点不能变动，因此我们需要一个辅助变量temp
        HeroNode temp = this.head;
        // 遍历链表，找到最后节点
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        // 当退出while循环时，temp就指向了链表的最后
        // 将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    /**
     * 添加节点到单向链表（考虑编号顺序）
     */
    public void addByOrder(HeroNode heroNode) {
        // 由于头节点不能变动，因此我们需要一个辅助指针（变量）来帮助找到添加的位置
        // 该临时指针应该是待添加数据位置的前一个节点
        HeroNode temp = this.head;
        // flag标志添加的编号是否存在，默认为false
        boolean flag = false;
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }

            if (temp.next.no > heroNode.no) {   // 位置找到，插入到temp后面即可
                break;
            } else if (temp.next.no == this.head.no) {   // 说明希望添加的heroNode的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next;
        }

        // 判断flag的值
        if (flag) { // 不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n", heroNode.no);
        } else {
            // 插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点的信息，根据编号来修改
     */
    public void update(HeroNode newHeroNode) {
        // 判断是否为空
        if (this.head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        // 根据编号找到需要修改的节点
        // 定义一个辅助变量
        HeroNode temp = this.head.next;
        // 表示是否找到该节点
        boolean flag = false;
        while (true) {
            // 已经遍历完链表
            if (temp == null) {
                break;
            }
            // 找到
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        // 根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号为%d的节点，不能修改\n", newHeroNode.no) ;
        }
    }

    /**
     * 删除节点
     *
     * 思路
     *  1、head节点不能变动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
     *  2、我们在比较时，通过temp.next.no和需要删除的节点的no比较
     */
    public void delete(int no) {
        HeroNode temp = this.head;
        // 标志是否找到待删除的节点
        boolean flag = false;
        while (true) {
            // 已经到链表的最后
            if (temp.next == null) {
                break;
            }
            // 找到待删除节点的前一个节点
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 判断flag
        if (flag) { // 找到
            // 删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的编号为%d的节点不存在\n", no);
        }
    }

    /**
     * 显示链表[遍历]
     */
    public void list() {
        // 判断链表是否为空
        if (this.head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        // 因为头节点不能变动，因此我们需要一个辅助变量来遍历
        HeroNode temp = this.head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点信息
            System.out.println(temp);
            // 将temp后移
            temp = temp.next;
        }
    }
}

/**
 * 定义HeroNode，每个HeroNode对象就是一个节点
 */
class HeroNode {
    /**
     * 编号
     */
    public int no;
    /**
     * 姓名
     */
    public String name;
    /**
     * 绰号
     */
    public String nickName;
    /**
     * 指向下一个节点
     */
    public HeroNode next;

    /**
     * 构造器
     * @param no
     * @param name
     * @param nickName
     */
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +
                '}';
    }
}
