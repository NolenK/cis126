public class TwoComplementer {
    
    boolean carry = true;
    public void twosComplement (boolean[] b)
	{	
		//complement
		for (int i =0; i <= b.length; i++)
		{
			b[i] =! b[i];
		}
		
		//2's complement
		for (int i = b.length; i >= 0; i--)
		{
			if (b[i] == true && carry == true)
			{
				carry = true;
				b[i] =! b[i];
			}
			else if (carry == true)
			{
				b[i] =! b[i];
				carry =! carry;
				break;
						
			}
		}
     }
}
