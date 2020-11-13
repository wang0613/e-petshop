package com.qhit.petshop.test;

import com.qhit.petshop.pojo.Pet;
import com.qhit.petshop.pojo.PetOwner;
import com.qhit.petshop.pojo.PetStore;
import com.qhit.petshop.service.PetOwnerService;
import com.qhit.petshop.service.PetService;
import com.qhit.petshop.service.PetStoreService;
import com.qhit.petshop.service.impl.PetOwnerServiceImpl;
import com.qhit.petshop.service.impl.PetServiceImpl;
import com.qhit.petshop.service.impl.PetStoreServiceImpl;

import java.util.List;
import java.util.Scanner;

/**
 * 主方法测试类
 * @author 王海龙
 */
public class Test {

    private static PetStoreService petStoreService = new PetStoreServiceImpl();
    private static PetOwnerService petOwnerService = new PetOwnerServiceImpl();
    private static PetService petService = new PetServiceImpl();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {


        System.out.println("欢迎来到宠物商店");
        System.out.println("Wonderland醒来，所有宠物从MySQL中醒来");
        System.out.println("-----------------------------------");
        System.out.println("序号\t" + "宠物名称");
        List<Pet> petList = petService.getPetList();
        for (Pet pet : petList) {
            System.out.println(pet.getId() + "\t" + pet.getName());
        }
        System.out.println("----------------------------------" + "\n");
        System.out.println("所有的主人从MySQL中醒来");
        System.out.println("----------------------------------");
        System.out.println("序号\t" + "主人名称");
        List<PetOwner> petOwners = petOwnerService.findAllPetOwner();
        for (PetOwner petOwner : petOwners) {
            System.out.println(petOwner.getId() + "\t" + petOwner.getName());
        }
        System.out.println("----------------------------------" + "\n");
        System.out.println("所有的宠物商店从MySQL中醒来");
        System.out.println("----------------------------------");
        System.out.println("序号\t" + "商店名称");
        List<PetStore> petStoreList = petStoreService.findPetStoreList();
        for (PetStore petStore : petStoreList) {
            System.out.println(petStore.getId() + "\t" + petStore.getName());
        }

        System.out.println("请选择登录模式，输入1为主人登录模式，输入2为商店登录模式");
        int choose = input.nextInt();
        boolean flag = true;
        while (flag) {
            if (choose == 1) {
                petOwnerlogin();
                flag = false;
            } else if (choose == 2) {
                petStorelogin();
                flag = false;
            } else {
                System.out.println("输入有误,请重新输入：");
                System.out.println("请选择登录模式，输入1为主人登录模式，输入2为主人登录模式");
                flag = true;
                choose = input.nextInt();
            }

        }


    }

    //主人登录的方法
    public static void petOwnerlogin() {
        boolean flag = true;
        while (flag) {
            System.out.println("请输入主人的名字：");
            String username = input.next();
            System.out.println("请输入主人的密码");
            String password = input.next();
            PetOwner login = petOwnerService.login(username, password);

            if (login != null) {
                flag = false;
                System.out.println("---恭喜您，登录成功！！---");
                System.out.println("---您的基本信息为：---");
                System.out.println("名字：" + login.getName());
                System.out.println("元宝数:" + login.getMoney());
                //参数为当前的主人
                buyPet(login);
            } else {
                System.out.println("用户名或密码输入有误，请重新输入！！");
                flag = true;
            }
        }


    }

    //主人交易宠物
    public static void buyPet(PetOwner petOwner) {
        System.out.println("登录成功，您可以购买和卖出宠物，按其他键退出");
        System.out.println("1: 购买宠物");
        System.out.println("2: 卖出宠物");
        int choose = input.nextInt();
        if (choose == 1) {
            System.out.println("------以下是库存宠物：-------");
            System.out.println("序号\t宠物名称\t类型\t元宝数");
            //查询宠物中商店不为null
            List<Pet> storePetList = petService.getStorePetList();
            for (Pet pet : storePetList) {
                System.out.println(pet.getId() + "\t" + pet.getName() + "\t" + pet.getType_name() + "\t" + "50");
            }
            System.out.println("请选择要购买哪一宠物，并输入序号：");
            int buyPetId = input.nextInt();
            petOwnerService.buyer(buyPetId, petOwner.getId());
            System.out.println("购买成功！！");
        } else if (choose == 2) {
            System.out.println("-----我的宠物列表-----");
            System.out.println("序号\t宠物名称\t类型");
            List<Pet> petListByOwnerId = petService.getPetListByOwnerId(petOwner.getId());
            for (Pet pet : petListByOwnerId) {
                System.out.println(pet.getId() + "\t" + pet.getName() + "\t" + pet.getType_name() + "\t" + "50");
            }
            System.out.println("请选择要出售的宠物序号：");
            int sellerPetId = input.nextInt();
            System.out.println("您要卖出的宠物信息如下：");
            Pet sellerPet = petService.findByPetId(sellerPetId);
            System.out.println("宠物的名字叫做:" + sellerPet.getName() + "宠物类型是：" + sellerPet.getType_name());
            System.out.println("请选择是否要卖出：y代表卖出，n代表取消");
            String confirmSeller = input.next();
            if (confirmSeller.equals("y")) {
                //查询出所有的商店
                System.out.println("下面是现有宠物商店，请选择您要卖给的卖家商店编号：");
                System.out.println("序号+\t" + "宠物商店名称");
                List<PetStore> petStoreList = petStoreService.findPetStoreList();
                for (PetStore petStore : petStoreList) {
                    System.out.println(petStore.getId() + "\t" + petStore.getName());
                }
                int chooseStoreId = input.nextInt();
                //主人卖出宠物
                petOwnerService.seller(sellerPetId, chooseStoreId, petOwner.getId());
                System.out.println("台账正确插入一条信息");
            } else if (confirmSeller.equals("n")) {
                System.out.println("您已取消卖出");
            }

        }


    }

    //商店登录
    public static void petStorelogin() {
        boolean flag = true;
        while (flag) {
            System.out.println("请输入商店的名字：");
            String username = input.next();
            System.out.println("请输入商店的密码");
            String password = input.next();
            PetStore login = petStoreService.login(username, password);

            if (login != null) {
                flag = false;
                System.out.println("---恭喜您，登录成功！！---");
                System.out.println("---宠物商店基本信息为：---");
                System.out.println("名字：" + login.getName());
                System.out.println("元宝数:" + login.getBalance());
                petStoreMenu(); //选择菜单
            } else {
                System.out.println("用户名或密码输入有误，请重新输入！！");
                flag = true;
            }
        }


    }
    //商店的菜单选择
    public static void petStoreMenu() {
        System.out.println("登录成功！！,可以进行如下操作");
        System.out.println("1.购买宠物");
        System.out.println("2.卖出宠物");
        System.out.println("3.培育宠物");
        System.out.println("4.查询代售出宠物");
        System.out.println("5.查看商店结余");
        System.out.println("6.查看商店账目");
        System.out.println("7.开宠物商店");
        System.out.println("请选择要执行的操作，按序号输入，退出请按0");
        boolean flag = true;
        while (flag){
            int chooseMenu = input.nextInt();
            switch (chooseMenu) {
                case 1:
                    System.out.println("购买宠物");
                    flag = false;
                    break;
                case 2:
                    System.out.println("卖出宠物");
                    flag = false;
                    break;
                case 3:
                    System.out.println("培育宠物");
                    flag = false;
                    break;
                case 4:
                    System.out.println("查询代售出宠物");
                    flag = false;
                    break;
                case 5:
                    System.out.println("查看商店结余");
                    flag = false;
                    break;
                case 6:
                    System.out.println("查看商店账目");
                    flag = false;
                    break;
                case 7:
                    System.out.println("开宠物商店");
                    flag = false;
                    break;
                case 0:
                    System.out.println("欢迎下次光临！！");
                    flag = false;
                    break;
                default:
                    System.out.println("输入有误，请重新输入！");
                    flag = true;

            }

        }

    }
}

