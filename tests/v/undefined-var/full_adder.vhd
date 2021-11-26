entity full_adder is port (
    A, B, Cin: in bit;
    S, Cout: out bit
);
end full_adder;

architecture full_adder_arch of full_adder is 
begin  
    S <= (A xor B) xor Cin;
    Cout <= ((A xor B) and Cin) or (A1 and B);
end full_adder_arch;

