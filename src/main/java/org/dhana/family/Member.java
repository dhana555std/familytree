package org.dhana.family;

import java.util.*;

public class Member {
    private String name;
    private int age;
    private Character gender;
    private Member father;
    private Member mother;
    private List<Member> kids;
    private final String id;

    public Member() {
        this.id = UUID.randomUUID().toString();
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Member getFather() {
        return father;
    }

    public Member getMother() {
        return mother;
    }

    public List<Member> getKids() {
        return kids;
    }

    public Character getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public void addParent(Member parent) {
        parent.kids = (parent.kids == null || parent.kids.isEmpty()) ? new ArrayList<>() : parent.getKids();

        parent.kids.add(this);
        if (parent.gender.equals('M')) {
            this.father = parent;
        } else {
            this.mother = parent;
        }

    }

    public void addKid(Member kid) {
        this.kids = (this.kids == null || this.kids.isEmpty()) ? new ArrayList<>() : this.getKids();
        this.kids.add(kid);
        if (this.gender.equals('M')) {
            kid.father = this;
        } else {
            kid.mother = this;
        }
    }

    Set<Member> displayFamilyMembers(Comparator<Member> sortOrder) {
        Set<Member> members = new TreeSet<>(sortOrder);
        members.add(this);
        addAncestors(this, members);
        addDescendants(this, members);
        return members;
    }

    private void addAncestors(Member member, Set<Member> members) {
        if (member.father != null) {
            members.add(member.father);
            addAncestors(member.father, members);
        }

        if (member.mother != null) {
            members.add(member.mother);
            addAncestors(member.mother, members);
        }
    }

    private void addDescendants(Member member, Set<Member> members) {
        members.add(member);
        if (member.kids != null && !member.kids.isEmpty()) {
            for (Member kid : member.kids) {
                addDescendants(kid, members);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Member)) {return false;}
        Member mem = (Member) o;
        return mem.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Member{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", gender=" + gender +
               ", id='" + id + '\'' +
               '}';
    }
}
