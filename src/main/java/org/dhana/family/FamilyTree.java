package org.dhana.family;

import java.util.*;

public class FamilyTree {
    private final Map<String, Member> families = new HashMap<>();
    private final Comparator<Member> comparator = Comparator.comparingInt(Member::getAge)
                                              .thenComparing(Member::getName)
                                              .thenComparing(Member::getGender)
                                              .thenComparing(Member::getId).reversed();

    public void addFamily(String name, Member member) {
        families.put(name, member);
    }

    public Member getFamilyDetails(String name) {
        return families.get(name);
    }

    public Set<Member> displayFamilyMembersByAge(String name) {

        return families.get(name).displayFamilyMembers(comparator);
    }

    public Set<Member> addMemberToSortedFamily(Set<Member> members, Member member) {
        members.add(member);
        new TreeSet<>(comparator).addAll(members);
        return members;
    }
}
