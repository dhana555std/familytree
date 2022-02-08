package org.dhana.family;

import java.util.*;

import org.dhana.utils.MemberUtils;

/**
 * @author Dhanapathi Marepalli
 * Class representing the family tree.
 */
public class FamilyTree {
    private final Map<String, Member> families = new HashMap<>();

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
    public List<Member> displayFamilyMembersByAge(String name) {
        return families.get(name).displayFamilyMembers();
    }

    /**
     * Add a member to the sorted family ensuring that the new person is added in the same sorted order.
     * @param members {@link Set} of family {@link Member}s in the sorted order.
     * @param member New family {@link Member} to be added.
     * @return {@link Set} of family {@link Member}s in the sorted order with the new {@link Member} included.
     */
    public List<Member> addMemberToSortedFamily(Member member, List<Member> members) {
        return MemberUtils.addMemberInSortedOrder(member, members);
    }
}
