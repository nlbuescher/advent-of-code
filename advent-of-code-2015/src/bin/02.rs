use advent_of_code_2015::*;
use code_timing_macros::*;
use std::fs::File;
use std::io::{BufRead, BufReader};
use std::ops::Add;
use std::str::FromStr;

const DAY: &str = "02";
const INPUT_FILE: &str = "src/input/02.txt";

const TEST: &str = "\
2x3x4
";

fn main() -> Result<()> {
    start_day(DAY);

    //region Part 1
    println!("=== Part 1 ===");

    fn part1<R: BufRead>(reader: R) -> Result<usize> {
        let input = reader.lines().flatten()
          .map(|line| {
            line.split('x')
              .flat_map(|it| usize::from_str(it))
              .collect::<Vec<_>>()
          })
          .collect::<Vec<_>>();

        let answer = input.iter().fold(0, |total_area, package| {
            let areas = package.iter()
              .enumerate()
              .flat_map(|(i, x)| {
                  package.iter()
                    .enumerate()
                    .filter(move |&(j, _)| i != j)
                    .map(move |(_, y)| (*x, *y))
              })
              .map(|(w, h)| w * h)
              .collect::<Vec<_>>();

            let smallest = areas.iter().min().unwrap().clone();

            total_area + areas.iter().fold(smallest, Add::add)
        });

        Ok(answer)
    }

    assert_eq!(58, part1(BufReader::new(TEST.as_bytes()))?);

    let input_file = BufReader::new(File::open(INPUT_FILE)?);
    let result = time_snippet!(part1(input_file)?);
    println!("Result = {}", result);
    //endregion

    //region Part 2
    // println!("\n=== Part 2 ===");
    //
    // fn part2<R: BufRead>(reader: R) -> Result<usize> {
    //     Ok(0)
    // }
    //
    // assert_eq!(0, part2(BufReader::new(TEST.as_bytes()))?);
    //
    // let input_file = BufReader::new(File::open(INPUT_FILE)?);
    // let result = time_snippet!(part2(input_file)?);
    // println!("Result = {}", result);
    //endregion

    Ok(())
}
