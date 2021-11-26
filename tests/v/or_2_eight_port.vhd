entity OR_gate_2 is port (
    x , y: in bit;
    F: out bit
);
end OR_gate_2;

architecture OR_gate_arch of OR_gate_2 is begin  
    F <= x or y;
end OR_gate_arch;

entity four_port_OR_gate_2 is port (
    a,b,c,d : in bit;
    result : out bit
);
end four_port_OR_gate_2;

architecture four_port_structure of four_port_OR_gate_2 is
    signal e, f : bit;
begin
    OR1: entity work.OR_gate_2 port map(a,b,e);
    OR2: entity work.OR_gate_2 port map(c,d,f);
    result <= e or f;
end four_port_structure;

entity eight_port_OR_gate_2 is port (
    x0, x1, x2, x3, x4, x5, x6, x7 : in bit;
    y : out bit
);
end eight_port_OR_gate_2;

architecture eight_port_structure of eight_port_OR_gate_2 is
    signal result1, result2 : bit;
begin
    OR1: entity work.four_port_OR_gate_2 port map(x0, x1, x2, x3, result1);
    OR2: entity work.four_port_OR_gate_2 port map(x4, x5, x6, x7, result2);
    y <= result1 or result2;
end eight_port_structure;
