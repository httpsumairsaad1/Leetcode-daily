class Solution {
    public int[] frequencySort(int[] nums) {
        int n = nums.length;
        int ans[] = new int[n];
        Map<Integer ,Integer> map = new HashMap<>();

        for(int a : nums){
            map.put(a , map.getOrDefault(a , 0) + 1);
        }

        List<Map.Entry<Integer , Integer>> list = new ArrayList<>(map.entrySet());

        Collections.sort( list , new Comparator<Map.Entry<Integer,Integer>>(){
            public int compare(Map.Entry<Integer , Integer> m1 , Map.Entry<Integer,Integer> m2){
                if(m1.getValue() == m2.getValue()){
                    return m2.getKey() - m1.getKey();
                }else{
                    return m1.getValue() - m2.getValue();
                }
            }
        });

        int k = 0;

        for(Map.Entry<Integer , Integer> m : list){
            int val = m.getValue();

            while(val > 0){
                ans[k++] = m.getKey();
                val--;
            }
        }

        return ans;
    }
}
