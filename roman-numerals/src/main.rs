use std::collections::HashMap;

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
    let map = create_roman_map();
    let keys = get_reverse_sorted_keys_of(&map);
    let mut roman = String::from("");

    while n > 3 {
        if map.contains_key(&(n + 1)) {
            roman.push('I');
            n += 1;
        }

        for &i in keys.to_owned() {
            let value = map.get(&i).unwrap();
            while n >= i {
                roman.push_str(value);
                n -= i;

                if map.contains_key(&(n + 1)) {
                    roman.push('I');
                    n += 1;
                }
            }
        }
    }
    
    for _ in 0..n {
        roman.push('I');
    }

    return roman;
}

fn create_roman_map() -> HashMap<u32, String> {
    let mut map: HashMap<u32, String> = HashMap::new();
    map.insert(5, String::from("V"));
    map.insert(10, String::from("X"));
    map.insert(40, String::from("XL"));
    map.insert(50, String::from("L"));
    map.insert(90, String::from("XC"));
    map.insert(100, String::from("C"));
    map.insert(400, String::from("CD"));
    map.insert(500, String::from("D"));
    map.insert(900, String::from("CM"));
    map.insert(1000, String::from("M"));
    map
}

fn get_reverse_sorted_keys_of(map: &HashMap<u32, String>) -> Vec<&u32> {
    let mut keys: Vec<&u32> = map.keys().collect();
    keys.sort();
    keys.reverse();
    keys
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
        assert_eq!("ID", to_roman(499));
        assert_eq!("D", to_roman(500));
        assert_eq!("DI", to_roman(501));
        assert_eq!("DCC", to_roman(700));
        assert_eq!("M", to_roman(1000));
        assert_eq!("MM", to_roman(2000));
        assert_eq!("MMXIX", to_roman(2019));
    }

}
