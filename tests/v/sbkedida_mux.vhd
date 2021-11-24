entity Mux is port(
    I5,I4,I3,I2,I1,I0,S: in bit;
    O0,O1,O2: out bit
);
end Mux;  

architecture behv1 of Mux is
begin
  process(I5,I4,I3,I2,I1,I0,S)
    begin
        if (S = '0') then
            O0 <= I0;
            O1 <= I2;
            O2 <= I4;
        else
            O2 <= I5;
            O0 <= I1;
            O1 <= I3;
        end if;
    end process; 
end behv1;


