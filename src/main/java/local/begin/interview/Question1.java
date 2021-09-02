package local.begin.interview;

public class Question1 {
    /**
     * 360 面试
     * @param origin
     * @return
     */
    public String sortString(String origin){
        int[] number = new int[10];
        int[] lettersCap = new int[26];
        int[] lettersLit = new int[26];
        for (char charactor: origin.toCharArray()) {
            if(charactor>= '0' && charactor<= '9'){
                number[charactor - '0']++;
            }
            if(charactor>='A' && charactor <= 'Z'){
                lettersCap[charactor - 'A']++;
            }
            if(charactor >= 'a' && charactor <= 'z'){
                lettersLit[charactor - 'a']++;
            }
        }
        StringBuffer rst = new StringBuffer();

        for(int i = 0; i < 10 ; i++){
            if(number[i] > 0){
                for(int j = 0; j < number[i]; j++){
                    rst.append((char) ('0'+i));
                }
            }
        }
        for(int i = 0; i < 26 ; i++){
            if(lettersCap[i] > 0){
                for(int j = 0; j < lettersCap[i]; j++){
                    rst.append((char) ('A'+i));
                }
            }
        }
        for(int i = 0; i < 26 ; i++){
            if(lettersLit[i] > 0){
                for(int j = 0; j < lettersLit[i]; j++){
                    rst.append((char)('a'+i));
                }
            }
        }
        return rst.toString();
    }

    public static void main(String[] args) {
        Question1 q = new Question1();
        System.out.println(q.sortString("1srogna049ndfag;0fal20hmsldf"));
    }

}
