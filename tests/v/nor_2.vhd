entity NOR_gate is port(
    x,y: in bit;
    F: out bit
);
end NOR_gate;  

architecture NOR_gate_arch of NOR_gate is 
begin 

    F <= x nor y; 

end NOR_gate_arch;

