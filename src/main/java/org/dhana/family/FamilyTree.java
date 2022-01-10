package org.dhana.family;

import java.util.*;

/**
 * @author Dhanapathi Marepalli
 * Class representing the family tree.
 */
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

    /**
     * Display members of a family passed in, based on the sorted order of Age.
     *
     * @param name the family name.
     * @return {@link Set} of family members in the sorted order by age.
     */
    public Set<Member> displayFamilyMembersByAge(String name) {
        return families.get(name).displayFamilyMembers(comparator);
    }

    /**
     * Add a member to the sorted family ensuring that the new person is added in the same sorted order.
     * @param members {@link Set} of family {@link Member}s in the sorted order.
     * @param member New family {@link Member} to be added.
     * @return {@link Set} of family {@link Member}s in the sorted order with the new {@link Member} included.
     */
    public Set<Member> addMemberToSortedFamily(Set<Member> members, Member member) {
        members.add(member);
        new TreeSet<>(comparator).addAll(members);
        return members;
    }
}
