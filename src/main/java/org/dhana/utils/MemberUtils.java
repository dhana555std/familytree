package org.dhana.utils;

import java.util.List;

import org.dhana.family.Member;

public class MemberUtils {
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
}
