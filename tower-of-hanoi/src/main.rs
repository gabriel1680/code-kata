fn main() {
    let mut tower = Tower::new(1);
    let _ = tower.get_current_floor();
    let _ = tower.next_floor();
}

struct Floor {
    pins: Vec<Vec<i32>>,
}

struct Tower {
    disks: i32,
    floors: Vec<Floor>,
    current_floor: usize,
}

impl Tower {
    pub fn new(disks: i32) -> Tower {
        let pins: Vec<Vec<i32>> = vec![(1..disks + 1).collect(), vec![], vec![]];
        let mut solver = Tower {
            disks,
            floors: vec![Floor { pins }],
            current_floor: 0,
        };
        solver.solve();
        return solver;
    }

    pub fn next_floor(&mut self) -> Result<Vec<Vec<i32>>, String> {
        if self.current_floor == self.floors.len() - 1 {
            return Err("Sequence contains no more elements".to_string());
        }
        self.current_floor += 1;
        return Ok(self.get_current_floor());
    }

    fn solve(&mut self) {
        if self.disks > 0 {
            self.hanoi(self.disks, 0, 1, 2)
        }
    }

    fn hanoi(&mut self, disks: i32, from: usize, to: usize, via: usize) {
        if disks == 1 {
            self.move_to(from, to);
        } else {
            self.hanoi(disks - 1, from, via, to);
            self.move_to(from, to);
            self.hanoi(disks - 1, via, to, from);
        }
    }

    fn move_to(&mut self, from: usize, to: usize) {
        let mut last_floor = Tower::copy(&self.floors.last().unwrap().pins);
        let disk = last_floor[from].pop().unwrap();
        last_floor[to].push(disk);
        self.floors.push(Floor { pins: last_floor });
    }

    fn copy(vec: &Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let mut result: Vec<Vec<i32>> = vec![];
        for value in vec.iter() {
            result.push(value.to_vec());
        }
        result
    }

    pub fn get_current_floor(&self) -> Vec<Vec<i32>> {
        Tower::copy(&self.floors[self.current_floor].pins)
    }
}

#[cfg(test)]
mod tests {

    use super::*;

    #[test]
    fn empty_tower() {
        let tower = Tower::new(0);
        let expected: Vec<Vec<i32>> = vec![vec![], vec![], vec![]];
        assert_eq!(expected, tower.get_current_floor());
    }

    #[test]
    fn error_when_not_have_more_steps() {
        let mut tower = Tower::new(0);
        let message = String::from("Sequence contains no more elements");
        assert_eq!(Err(message), tower.next_floor());
    }

    #[test]
    fn should_resolve_one_disk() {
        let tower = Tower::new(1);
        assert_eq!(vec![vec![1], vec![], vec![]], tower.get_current_floor());
    }

    #[test]
    fn should_resolve_next_step() {
        let mut tower = Tower::new(1);
        assert_eq!(Ok(vec![vec![], vec![1], vec![]]), tower.next_floor());
    }

    #[test]
    fn should_resolve_two_disks() {
        let tower = Tower::new(2);
        assert_eq!(vec![vec![1, 2], vec![], vec![]], tower.get_current_floor());
    }

    #[test]
    fn next_step_three_disks() {
        let mut tower = Tower::new(3);
        assert_eq!(Ok(vec![vec![1, 2], vec![3], vec![]]), tower.next_floor());
        assert_eq!(Ok(vec![vec![1], vec![3], vec![2]]), tower.next_floor());
        assert_eq!(Ok(vec![vec![1], vec![], vec![2, 3]]), tower.next_floor());
        assert_eq!(Ok(vec![vec![], vec![1], vec![2, 3]]), tower.next_floor());
        assert_eq!(Ok(vec![vec![3], vec![1], vec![2]]), tower.next_floor());
        assert_eq!(Ok(vec![vec![3], vec![1, 2], vec![]]), tower.next_floor());
        assert_eq!(Ok(vec![vec![], vec![1, 2, 3], vec![]]), tower.next_floor());
    }
}
