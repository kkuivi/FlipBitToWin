public class flipBitWin {

	/*This program is my solution to the question: Flip Bit To Win --> You have an integer
	 * and you can flip exactly one bit from a 0 to a 1. Write code to find the length
	 * of the longest sequence of 1s you could create.
	 * Example: Input --> 1775 (or : 11011101111)
	 * 			Output --> 8*/
	public static void main(String[] args) {
		
	}

	private static int flipBitToWin(int num) {
		boolean encountered_zero = false; // checks whether first zero bit
											// encountered
		int left_seq = 0; // stores length of sequence of 1's to the left of
							// zero
		int right_seq = 0; // stores length of sequence of 1's to the right of
							// zero
		int max_seq = 0; // stores max sequence of 1's encountered
		int bit_index = 0; // will be used to get value of bit at given index
		int curr_seq = 0; // store intermediate/current sequence length when in
							// while loop
		boolean exploring_seq = true; // used to check whether the sequence end
										// has not been reached
		int SEQUENCE_LENGTH = 32; //max length of digits in binary representation of integer

		if(num == -1)
			return SEQUENCE_LENGTH;
		
		while (bit_index < SEQUENCE_LENGTH) {
			if(is1AtPos(bit_index, num)){ //if bit at bit_index is a 1
				if(!exploring_seq) //if the length of a sequence of 1's has been already checked and this sequence is the beginning of a new sequence of 1's
					exploring_seq = true;
				if(encountered_zero) 
					right_seq++;
				else
					left_seq ++;
			}
			else{
				if(encountered_zero){ //if another zero is encountered then terminate the current sequence, and calculate its length
					curr_seq = 1 + left_seq + right_seq; //sequence is equal to 1 (zero bit) + sequence length to the left of the zero bit + sequence length to the right of the zero bit
					max_seq = curr_seq > max_seq ? curr_seq : max_seq; //update max_seq if curr_seq is greater 
					left_seq = right_seq;
					right_seq = 0;
					exploring_seq = false; //indicates current sequence is done being explored
				}
				else
					encountered_zero = true;
			}
			bit_index ++;
		}
		
		if(exploring_seq){ //if num ends with a 1 then check the length of this sequence 
			curr_seq = 1 + left_seq + right_seq;
			max_seq = curr_seq > max_seq ? curr_seq : max_seq;
		}
		return max_seq;
	}
	
	/*checks if the bit at the given index is a 1*/
	static boolean is1AtPos(int i, int num){
		int mask = 1 << i; 
		return (num & mask) != 0 ? true : false;
	}
}
