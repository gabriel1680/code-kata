fn main() {
    println!("Type the decimal number that will be converted to roman: ");

    let mut line = String::new();
    std::io::stdin().read_line(&mut line).unwrap();

    let input_value: Result<u32, std::num::ParseIntError> = line.trim().parse();
    match input_value {
        Ok(value) => println!("Roman numeral: {}", to_roman(value)),
        Err(_) => println!("Invalid input."),
    }
}

pub fn to_roman(mut n: u32) -> String {
    let mut result = String::from("");
    for roman in Roman::VALUES {
        while n >= roman.as_int() {
            n -= roman.as_int();
            result.push_str(&roman.as_str());
        }
    }
    return result;
}

#[derive(Debug, Clone, Copy)]
pub enum Roman {
    M,
    CM,
    D,
    CD,
    C,
    XC,
    L,
    XL,
    X,
    IX,
    V,
    IV,
    I,
}

impl Roman {
    pub fn as_str(&self) -> String {
        let value = match self {
            Roman::M => "M",
            Roman::CM => "CM",
            Roman::D => "D",
            Roman::CD => "CD",
            Roman::C => "C",
            Roman::XC => "XC",
            Roman::L => "L",
            Roman::XL => "XL",
            Roman::X => "X",
            Roman::IX => "IX",
            Roman::V => "V",
            Roman::IV => "IV",
            Roman::I => "I",
        };
        String::from(value)
    }

    pub fn as_int(&self) -> u32 {
        match self {
            Roman::M => 1000,
            Roman::CM => 900,
            Roman::D => 500,
            Roman::CD => 400,
            Roman::C => 100,
            Roman::XC => 90,
            Roman::L => 50,
            Roman::XL => 40,
            Roman::X => 10,
            Roman::IX => 9,
            Roman::V => 5,
            Roman::IV => 4,
            Roman::I => 1,
        }
    }

    const VALUES: [Self; 13] = [
        Self::M,
        Self::CM,
        Self::D,
        Self::CD,
        Self::C,
        Self::XC,
        Self::L,
        Self::XL,
        Self::X,
        Self::IX,
        Self::V,
        Self::IV,
        Self::I,
    ];
}

#[cfg(test)]
mod tests {

    use super::*;

    #[test]
    fn translate_to_roman() {
        assert_eq!("I", to_roman(1));
        assert_eq!("II", to_roman(2));
        assert_eq!("III", to_roman(3));
        assert_eq!("IV", to_roman(4));
        assert_eq!("V", to_roman(5));
        assert_eq!("VI", to_roman(6));
        assert_eq!("VII", to_roman(7));
        assert_eq!("VIII", to_roman(8));
        assert_eq!("IX", to_roman(9));
        assert_eq!("X", to_roman(10));
        assert_eq!("XI", to_roman(11));
        assert_eq!("XII", to_roman(12));
        assert_eq!("XIII", to_roman(13));
        assert_eq!("XIV", to_roman(14));
        assert_eq!("XV", to_roman(15));
        assert_eq!("XVI", to_roman(16));
        assert_eq!("XVII", to_roman(17));
        assert_eq!("XVIII", to_roman(18));
        assert_eq!("XIX", to_roman(19));
        assert_eq!("XX", to_roman(20));
        assert_eq!("XXI", to_roman(21));
        assert_eq!("XXII", to_roman(22));
        assert_eq!("XXIV", to_roman(24));
        assert_eq!("XXV", to_roman(25));
        assert_eq!("L", to_roman(50));
        assert_eq!("LXX", to_roman(70));
        assert_eq!("C", to_roman(100));
        assert_eq!("CCXCIV", to_roman(294));
        assert_eq!("CDXCIX", to_roman(499));
        assert_eq!("D", to_roman(500));
        assert_eq!("DI", to_roman(501));
        assert_eq!("DCC", to_roman(700));
        assert_eq!("M", to_roman(1000));
        assert_eq!("MM", to_roman(2000));
        assert_eq!("MMXIX", to_roman(2019));
    }
}
