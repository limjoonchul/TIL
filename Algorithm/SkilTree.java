package com.company.Programmers;

public class SkilTree {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        if(skill.equals(skill.toLowerCase())){
            return -1;
        }else{
            if((skill.length()<1 || skill.length()>=27) && (skill_trees.length <1 && skill_trees.length >=21)){
                return -1;
            }else{
                for (int i=0; i<skill_trees.length; i++){
                    for (int j=0; j<skill_trees[i].length()-1; j++){
                        if(skill_trees[i].charAt(j) == skill.charAt(0)){
                            if(skill_trees[i].charAt(j+1) == skill.charAt(1)){
                                answer++;
                            }
                        }
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        SkilTree skilTree = new SkilTree();
        String skill = "cbd";
        String[] skill_trees = new String[]{"BACDE", "CBADF", "AECB", "BDA"};

        System.out.println(skilTree.solution(skill,skill_trees));


        
    }
}
