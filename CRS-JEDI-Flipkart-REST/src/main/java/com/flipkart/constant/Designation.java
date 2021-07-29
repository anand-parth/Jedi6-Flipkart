package com.flipkart.constant;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Class to assign professor designation
 *
 */
public enum Designation {
    LECTURER, ASSISTANT_PROFESSOR, ASSOCIATE_PROFESSOR, PROFESSOR, HOD;

    /*
    *
    * Method to convert designation to string
    *
    */
    public String toString() {
        switch (this) {
            case LECTURER:
                return "Lecturer";
            case ASSISTANT_PROFESSOR:
                return "Assistant Professor";
            case ASSOCIATE_PROFESSOR:
                return "Associate Professor";
            case PROFESSOR:
                return "Professor";
            case HOD:
                return "Head of Department";
            default:
                throw new IllegalArgumentException("Designation invalid!!!");
        }
    }

    /*
     *
     * Method to convert designation to rank
     *
     */
    public Integer toInt() {
        switch (this) {
            case LECTURER:
                return 1;
            case ASSISTANT_PROFESSOR:
                return 2;
            case ASSOCIATE_PROFESSOR:
                return 3;
            case PROFESSOR:
                return 4;
            case HOD:
                return 5;
            default:
                throw new IllegalArgumentException("Designation invalid!!!");
        }
    }

    /*
     * Method to get designation
     * @param designation rank
     *
     */
    public static Designation getDesignation(Integer designation){
        switch(designation){
            case 1:
                return LECTURER;
            case 2:
                return ASSISTANT_PROFESSOR;
            case 3:
                return ASSOCIATE_PROFESSOR;
            case 4:
                return PROFESSOR;
            case 5:
                return HOD;
            default:
                throw new IllegalArgumentException("Invalid Designation!!!");
        }
    }

    /**
     *
     * Method to cdesignation string
     *
     */
    public Designation getDesignation(String designation){
        switch(designation){
            case "Lecturer":
                return LECTURER;
            case "Assistant Professor":
                return ASSISTANT_PROFESSOR;
            case "Associate Professor":
                return ASSOCIATE_PROFESSOR;
            case "Professor":
                return PROFESSOR;
            case "Head of Department":
                return HOD;
            default:
                throw new IllegalArgumentException("Invalid Designation!!!");
        }
    }
}
