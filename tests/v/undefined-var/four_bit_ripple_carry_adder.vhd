entity full_adder is port (
    A,B,Cin: in bit;
    S,Cout: out bit
);
end full_adder;

architecture full_adder_arch of full_adder is 
begin  
    S <= (A xor B) xor Cin;
    Cout <= ((A xor B) and Cin) or (A and B);

end full_adder_arch;


entity half_adder is port (
    A,B: in bit;
    S,C: out bit
);
end half_adder;

architecture half_adder_arch of half_adder is 
begin  
    S <= not(A or B);
    C <= A and B;
end half_adder_arch;


entity four_bit_ripple_carry_adder is port (
    a0, b0, a1, b1, a2, b2, a3, b3, Cin : in bit; 
    sum0, sum1, sum2, sum3,Cout, V: out bit
);
end four_bit_ripple_carry_adder;


architecture fouradder_structure of four_bit_ripple_carry_adder is
     signal c1, c2, c3, c4: bit;
begin
    FA0 : entity work.full_adder port map(a0,b0,Cin,sum0,c1);
    FA1 : entity work.full_adder port map(a1,b1,c1,sum1,c2);
    FA2 : entity work.full_adder port map(a2,b2,c2,sum2,c3);
    FA3 : entity work.full_adder port map(a3,b3,c3,sum3,c4);
    
    V <= c xor c4;
    Cin <= c4;
end fouradder_structure;
