entity IfIf2 is port(
    I4,I5,I3,I2,I1,I0,S: in bit;
    O0,O1,O2,O3: out bit
);
end Mux;  

architecture behv1 of IfIf2 is
begin
  process(I3,I2,I1,I0,S)
    begin
        if (S = '0') then
            O0 <= I0;
            O1 <= I2;
        else
            O0 <= I1;
            O1 <= I3;
        end if;
        if (S = '1') then
            O2 <= I4;
            O3 <= I5;
        else
            O2 <= I5;
            O3 <= I3;
        end if;
    end process;
    
end behv1;


