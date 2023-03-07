package net.jordan.quran_club.resourses;

public class Constant {
   public static final String url="https://www.quranclubjordan.net/QuranClub/api/";
   public static final String USER_LOGIN="Userlogin";
   public static final String TEACHER="Teacher";
   public static final String SURAH="Surah";
   public static final String STUDENT_QURAN_PART="StudentQuranPart";
   public static final String STUDENT_LESSON="StudentLesson";
   public static final String STUDENT_LESSON_QURAN_PART_SURAH="StudentLessonQuranPartSurah";
   public static final String QURAN_PART_SURAH="QuranPartSurah";
   public static final String STUDENT="Student";
   public static final String QURAN_PART="QuranPart";
   public static final String LESSON="Lesson";
   public static final String IMAGE_LEVEL="ImageLevel";
   public static final String CLASS="Class";
   public static final String ADMIN="Admin";
   public static final String DONE_SUCCESSFULLY="تمت العملية بنجاح";
   public static final String ERROR="هنالك مشكلة في العملية";


   public static String detectStatus(String status){

      if (status.equals("success")){
         return DONE_SUCCESSFULLY;
      }
      else {
         return ERROR;
      }
   }



}
