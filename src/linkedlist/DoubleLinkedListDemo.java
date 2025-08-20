package linkedlist;

/**
 *
 * @author Mr.MC
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        // 添加
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.delete(3);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();
    }
}

/**
 * 创建一个双向链表的类
 */
class DoubleLinkedList {
    /**
     * 先初始化一个头节点（头节点不能变动，一旦变动就找不到该链表），头节点不存放具体数据
     */
    private HeroNode2 head = new HeroNode2(0, "", "");

    /**
     * 返回头节点
     * @return
     */
    public HeroNode2 getHead() {
        return this.head;
    }

    /**
     * 添加节点到双向链表（不考虑编号顺序）
     *
     * 思路：
     *  1、找到当前链表的最后节点
     *  2、将最后节点的next指向新的节点
     *
     * @param heroNode
     */
    public void add(HeroNode2 heroNode) {
        // 由于head节点不能变动，因此我们需要一个辅助变量temp
        HeroNode2 temp = this.head;
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
        // 将新添加节点的pre指向最后节点
        // 形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 添加节点到双向链表（考虑编号顺序）
     */
    public void addByOrder(HeroNode2 heroNode) {
        // 由于头节点不能变动，因此我们需要一个辅助指针（变量）来帮助找到添加的位置
        // 该临时指针应该是待添加数据位置的前一个节点
        HeroNode2 temp = this.head;
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
            if (temp.next != null) {
                temp.next.pre = heroNode;
            }
            heroNode.next = temp.next;
            temp.next = heroNode;
            heroNode.pre = temp;
        }
    }

    /**
     * 修改节点的信息，根据编号来修改
     */
    public void update(HeroNode2 newHeroNode) {
        // 判断是否为空
        if (this.head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        // 根据编号找到需要修改的节点
        // 定义一个辅助变量
        HeroNode2 temp = this.head.next;
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
     *  1、对于双向链表，我们可以直接找到要删除的节点
     *  2、找到后，自我删除即可（对比单向链表不需要再找前一个节点）
     */
    public void delete(int no) {
        // 判断当前链表是否为空
        if (this.head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }

        HeroNode2 temp = this.head.next;
        // 标志是否找到待删除的节点
        boolean flag = false;
        while (true) {
            // 已经到链表的最后
            if (temp == null) {
                break;
            }
            // 找到待删除节点的前一个节点
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 判断flag
        if (flag) { // 找到
            // 删除
            temp.pre.next = temp.next;
            // 如果是最后一个节点，就不需要执行
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
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
        HeroNode2 temp = this.head.next;
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
 * 定义HeroNode2，每个HeroNode2对象就是一个节点
 */
class HeroNode2 {
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
    public HeroNode2 next;
    /**
     * 指向前一个节点
     */
    public HeroNode2 pre;

    /**
     * 构造器
     * @param no
     * @param name
     * @param nickName
     */
    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +
                '}';
    }
}