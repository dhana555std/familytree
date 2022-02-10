package org.dhana.utils;

import java.util.List;

import org.dhana.family.Member;

/**
 * Utility class for Members.
 */
public class MemberUtils {
    /**
     * This method adds a {@link Member} to the {@link List} of {@link Member}s in the correct position of the
     * sorted order. Internally this method uses the
     * <a href="https://www.geeksforgeeks.org/jump-search/">Jump Search.</a> to find the correct position of the
     * new element to be placed in the sorted order.
     *
     * @param member the new {@link Member} to be added in the sorted order.
     * @param members the {@link List} of {@link Member}s to which the {@link Member} is to be added.
     * @return sorted {@link List} of {@link Member}s with the {@link Member} added in the correct sorting order.
     */
    public static List<Member> addMemberInSortedOrder(Member member, List<Member> members) {
        if (members.isEmpty()) {
            members.add(member);
            return members;
        }

        int step = (int) Math.floor(Math.sqrt(members.size()));
        int membersSize = members.size();
        int pointer = 0;

        while (members.get(Math.min(step, membersSize) - 1).getAge() < member.getAge() && step < membersSize) {
            pointer = step;
            step += step;
        }

        while (pointer < Math.min(step, membersSize)) {
            if (members.get(pointer).getAge() >= member.getAge()) {
                members.add(pointer, member);
                return members;
            }
            ++pointer;
        }
        members.add(membersSize, member);

        return members;
    }

    /**
     * Helper method to create a {@link Member}.
     * @param name name of the {@link Member}.
     * @param age age of the {@link Member}.
     * @param gender gender of the {@link Member}.
     * @return the {@link Member} created.
     */
    public static Member getMember(String name, int age, char gender) {
        Member member = new Member();
        member.setName(name);
        member.setAge(age);
        member.setGender(gender);
        return member;
    }
}
