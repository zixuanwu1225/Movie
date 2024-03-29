import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a title search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.contains(searchTerm))
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast()
    {
        System.out.print("Enter a title search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();
        ArrayList<Movie> newResults = new ArrayList<Movie>();
        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String cast = movies.get(i).getCast();
            cast = cast.toLowerCase();

            if (cast.contains(searchTerm))
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        int index=0;
        // now, display them all to the user
        ArrayList<String> secondList = new ArrayList<String>();
        for (int i = 0; i < results.size(); i++)
        {
            String actors = results.get(i).getCast();
            String[] actor = actors.split("\\|");
            ArrayList<String> list = new ArrayList<String>();
            for (String s : actor) {
                String check = s.toLowerCase();
                if (check.contains(searchTerm)) {
                    list.add(s);
                }
            }
            for (String s : list) {
                if (!secondList.contains(s)) {
                    secondList.add(s);
                }
            }

            // this will print index 0 as choice 1 in the results list; better for user!

        }
        int choiceNum = index + 1;
        while(index<secondList.size()) {
            System.out.println("" + choiceNum + ". " + secondList.get(index));
            index = index + 1;
            choiceNum++;
        }
        System.out.println("Which actor would you like to learn more about?");
        System.out.print("Enter number: ");
        int choice = scanner.nextInt()-1;
        scanner.nextLine();
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getCast();
            String[] movies = movieTitle.split("\\|");
            movieTitle = movieTitle.toLowerCase();
            for(String s:movies){
                if (s.equals(secondList.get(choice)))
                {
                    //add the Movie objest to the results list
                    newResults.add(this.movies.get(i));
                }
            }

        }
        for (int i = 0; i < newResults.size(); i++)
    {
        String title = newResults.get(i).getTitle();

        // this will print index 0 as choice 1 in the results list; better for user!
        int choiceNumber = i + 1;

        System.out.println("" + choiceNumber + ". " + title);
    }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice2 = scanner.nextInt();
        scanner.nextLine();
        Movie selectedMovie = newResults.get(choice2 - 1);


        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void searchKeywords()
    {
        System.out.print("Enter a title search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String keyword = movies.get(i).getKeywords();
            keyword = keyword.toLowerCase();

            if (keyword.contains(searchTerm))
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listGenres()
    {

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();
        ArrayList<Movie> newResults = new ArrayList<Movie>();
        ArrayList<String> list = new ArrayList<String>();
        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();
            //add the Movie objest to the results list
            results.add(movies.get(i));
            list.add(movies.get(i).getGenres());
        }
        sortResults(results);

        int index=0;
        // now, display them all to the user
        ArrayList<String> secondList = new ArrayList<String>();
        for (int i = 0; i < results.size(); i++)
        {
            String actors = results.get(i).getGenres();
            String[] actor = actors.split("\\|");

            for (String s : actor) {
                if (!secondList.contains(s)) {
                    secondList.add(s);
                }
            }

            // this will print index 0 as choice 1 in the results list; better for user!

        }
        int choiceNum = index + 1;
        while(index<secondList.size()) {
            System.out.println("" + choiceNum + ". " + secondList.get(index));
            index = index + 1;
            choiceNum++;
        }
        System.out.println("Which genre would you like to learn more about?");
        System.out.print("Enter number: ");
        int choice = scanner.nextInt()-1;
        scanner.nextLine();
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getGenres();
            String[] movies = movieTitle.split("\\|");
            movieTitle = movieTitle.toLowerCase();
            for(String s:movies){
                if (s.equals(secondList.get(choice)))
                {
                    //add the Movie objest to the results list
                    newResults.add(this.movies.get(i));
                }
            }

        }
        for (int i = 0; i < newResults.size(); i++)
        {
            String title = newResults.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNumber = i + 1;

            System.out.println("" + choiceNumber + ". " + title);
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice2 = scanner.nextInt();
        scanner.nextLine();
        Movie selectedMovie = newResults.get(choice2 - 1);


        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }


    private void listHighestRated()
    {
        ArrayList<Movie> movieResults = new ArrayList<Movie>();
        movieResults.add(movies.get(0));
        for (Movie mov : movies)
        {
            int i = 0;
            while (mov.getUserRating() < movieResults.get(i).getUserRating() && i < movieResults.size() - 1)
            {
                i++;
            }
            movieResults.add(i, mov);
        }

        // now, display them all to the user
        for (int i = 0; i < 50; i++)
        {
            String title = movieResults.get(i).getTitle();
            double rate = movieResults.get(i).getUserRating();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title + ": " + rate);
        }

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRevenue()
    {
        ArrayList<Movie> movieResults = new ArrayList<Movie>();
        movieResults.add(movies.get(0));
        for (Movie mov : movies)
        {
            int i = 0;
            while (mov.getRevenue() < movieResults.get(i).getRevenue() && i < movieResults.size() - 1)
            {
                i++;
            }
            movieResults.add(i, mov);
        }

        // now, display them all to the user
        for (int i = 0; i < 50; i++)
        {
            String title = movieResults.get(i).getTitle();
            int revenue = movieResults.get(i).getRevenue();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title + ": " + revenue);
        }

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}