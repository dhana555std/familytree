package org.dhana;

import java.util.List;
import java.util.Set;

import org.dhana.family.FamilyTree;
import org.dhana.family.Member;

/**
 * @author  Dhanapathi Marepalli.
 * The class where the Execution starts.
 *
 */
public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();
        Member member = getMember("Dhana", 36, 'M');

        //Add Parents
        Member father = getMember("Subrahmanyam", 63, 'M');
        Member mother = getMember("Surya Kumari", 58, 'F');
        member.addParent(father);
        member.addParent(mother);

        // Add KIDS
        Member kid1 = getMember("Gayatri", 9, 'F');
        Member kid2 = getMember("Pranavi", 5, 'F');
        member.addKid(kid1);
        member.addKid(kid2);

        // Add Grand KIDS
        Member gk1 = getMember("Kalyan", 1, 'M');
        Member gk2 = getMember("Meher", 1, 'F');
        kid1.addKid(gk1);
        kid1.addKid(gk2);

        Member gk3 = getMember("Chaitanya", 1, 'M');
        Member gk4 = getMember("Gayatri", 1, 'F');

        kid2.addKid(gk3);
        kid2.addKid(gk4);

        // Add Grand Parents
        Member grandFather = getMember("Sr. Dhanapathi", 85, 'M');
        Member grandMother = getMember("Kanaka Durga", 78, 'F');

        father.addParent(grandFather);
        father.addParent(grandMother);

        // Add Great Grand Parents
        Member greatGrandFather = getMember("Subba Rao", 94, 'M');
        Member greatGrandMother = getMember("Lalitha", 80, 'F');

        greatGrandFather.addKid(grandFather);
        greatGrandMother.addKid(grandFather);

        familyTree.addFamily("Marepalli", member);

        // Add new family.
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
        System.out.println("Grand children: ");
        marepalli.getKids().forEach(x -> System.out.println(x.getKids()));

        // Display members in the sorted order.
        List<Member> marepalliMembers = familyTree.displayFamilyMembersByAge("Marepalli");
        System.out.println("Members in the sorted order of age:-\n");
        marepalliMembers.forEach(System.out:: println);

        // Add a new Member to the existing sorted members ensuring he is in the right order.
        System.out.println("Add member to sorted family:-");
        marepalliMembers = familyTree.addMemberToSortedFamily(getMember("Gayatri Ananta", 58, 'F'), marepalliMembers);
        marepalliMembers.forEach(System.out:: println);
    }

    /**
     * Helper method to create a {@link Member}.
     * @param name name of the {@link Member}.
     * @param age age of the {@link Member}.
     * @param gender gender of the {@link Member}.
     * @return the {@link Member} created.
     */
    private static Member getMember(String name, int age, char gender) {
        Member member = new Member();
        member.setName(name);
        member.setAge(age);
        member.setGender(gender);
        return member;
    }
}
