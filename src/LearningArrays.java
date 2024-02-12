public class LearningArrays {
    public static void main(String[] args) {
        LearningArrays array = new LearningArrays();
    }
    public int[] nums = new int[999];

    public LearningArrays() {
        nums[0]=7;
        nums[1]=10;
        nums[2]=52;
        nums[3]=6;
        displayNums();
    }
    public void displayNums() {
        System.out.println(nums[0]);
        System.out.println(nums[1]);
        System.out.println(nums[2]);
        System.out.println(nums[3]);

        for (int num : nums) {
            System.out.println(num);
        }

        for (int i=0; i<nums.length; i++) {
            nums[i] = (int)(Math.random()*100);
            System.out.println(nums[i]);
        }
    }
}
