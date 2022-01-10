package org.dhana;

import java.util.Set;

import org.dhana.family.FamilyTree;
import org.dhana.family.Member;

public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();
        Member member = getMember("Dhana", 36, 'M');

        //Add Parents
        Member father = getMember("Subrahmanyam", 63, 'M');
        Member mother = getMember("Surya Kumari", 58, 'F');
        member.addParent(father);
        member.addParent(mother);

        // KIDS
        Member kid1 = getMember("Gayatri", 9, 'F');
        Member kid2 = getMember("Pranavi", 5, 'F');
        member.addKid(kid1);
        member.addKid(kid2);

        // Grand KIDS
        Member gk1 = getMember("Kalyan", 1, 'M');
        Member gk2 = getMember("Meher", 1, 'F');
        kid1.addKid(gk1);
        kid1.addKid(gk2);

        Member gk3 = getMember("Chaitanya", 1, 'M');
        Member gk4 = getMember("Gayatri", 1, 'F');

        kid2.addKid(gk3);
        kid2.addKid(gk4);

        // Grand Parents
        Member grandFather = getMember("Sr. Dhanapathi", 85, 'M');
        Member grandMother = getMember("Kanaka Durga", 78, 'F');

        father.addParent(grandFather);
        father.addParent(grandMother);

        // Great Grand Parents
        Member greatGrandFather = getMember("Subba Rao", 94, 'M');
        Member greatGrandMother = getMember("Lalitha", 80, 'F');

        greatGrandFather.addKid(grandFather);
        greatGrandMother.addKid(grandFather);

        familyTree.addFamily("Marepalli", member);

        // Kaka family.
        Member kaka = getMember("Kaka", 31, 'M');
        Member kakaNanna = getMember("Kaka", 61, 'M');
        Member kakaAmma = getMember("Kaka", 51, 'F');

        kaka.addParent(kakaNanna);
        kakaAmma.addKid(kaka);

        familyTree.addFamily("kaka", kaka);

        Member marepalli = familyTree.getFamilyDetails("Marepalli");
        System.out.println("Member: " + marepalli);
        System.out.println("Father: " + marepalli.getFather());
        System.out.println("Mother: " + marepalli.getMother());
        System.out.println("Kids: " + marepalli.getKids());
        System.out.println("Grand Father: " + marepalli.getFather().getFather());
        System.out.println("Great Grand Father: " + marepalli.getFather().getFather().getFather());
        marepalli.getKids().forEach(x -> System.out.println(x.getKids()));

        Set<Member> marepalliMembers = familyTree.displayFamilyMembersByAge("Marepalli");

        System.out.println("Members in the sorted order of age:-\n");
        marepalliMembers.forEach(System.out:: println);

        System.out.println("Add member to sorted family:-");
        marepalliMembers = familyTree.addMemberToSortedFamily(marepalliMembers, getMember("Parvthi", 2, 'F'));
        marepalliMembers.forEach(System.out:: println);
    }

    private static Member getMember(String name, int age, char gender) {
        Member member = new Member();
        member.setName(name);
        member.setAge(age);
        member.setGender(gender);
        return member;
    }
}
