import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class MPilot
{
	public static void main (String[] args) throws java.lang.Exception
	{
        //TODO: validate input

        boolean test = false;

        List<Pilot> pilots = new ArrayList<>();
        if(!test) {
int x = 1;
            int y = -(x+1);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            int pilotListSize = Integer.valueOf(line);
            for (int i = 0; i < pilotListSize; i++) {
                line = br.readLine();
                String[] salaries = line.split(" ");
                pilots.add(new Pilot(Integer.valueOf(salaries[0]), Integer.valueOf(salaries[1])));
            }
        } else {
            // TEST TEST
            List<int[]> allSalaries = new ArrayList<>();
            allSalaries.add(new int[]{10000, 7000});
            allSalaries.add(new int[]{9000, 3000});
            allSalaries.add(new int[]{6000, 4000});
            allSalaries.add(new int[]{5000, 1000});
            allSalaries.add(new int[]{9000, 3000});
            allSalaries.add(new int[]{8000, 6000});
            pilots = new ArrayList<>();
            for (int[] salaries : allSalaries) {
                pilots.add(new Pilot(salaries[0], salaries[1]));
            }
            // TEST TEST
        }
        List<Map.Entry<Pilot, Pilot>> teams = getPilotTeams(pilots);

        int totalPayroll = 0;
        for(Map.Entry<Pilot, Pilot> team : teams) {
            totalPayroll += team.getKey().capt + team.getValue().asst;
        }

        System.out.println(totalPayroll);
	}

    // Entry<capt, asst>
	public static List<Map.Entry<Pilot, Pilot>> getPilotTeams(List<Pilot> pilots) {
		List<Map.Entry<Pilot, Pilot>> teams = new ArrayList<>();
        Set<Pilot> done = new HashSet<>();
        PriorityQueue<Pilot> pq = new PriorityQueue<>(
                pilots.size(), (Comparator<Pilot>) (o1, o2) -> (o2.capt - o2.asst) - (o1.capt - o1.asst));
        pq.addAll(pilots);

        while(!pq.isEmpty()) {
            Pilot asst = pq.poll();
            if(done.contains(asst)) {
                continue;
            }

            Pilot capt = getCaptain(asst, pilots, done);
            if(null != capt) {
                add(asst, capt, done, teams);
            } // else, already popped, do nothing
        }

        return teams;
	}

    public static Pilot getCaptain(Pilot asst, List<Pilot> pilots, Set<Pilot> done) {
        int asstIndex = pilots.indexOf(asst);
        Pilot capt = null;
        for(int i = asstIndex + 1; i < pilots.size(); i++) {
            if(!done.contains(pilots.get(i)) && (null == capt || pilots.get(i).salaryDiffDiff(capt) < 0)) {
                capt = pilots.get(i);
            }
        }

        return capt;
    }

    public static void add(Pilot asst, Pilot capt, Set<Pilot> done, List<Map.Entry<Pilot, Pilot>> teams) {
        done.add(asst);
        done.add(capt);
        teams.add(new AbstractMap.SimpleEntry<>(capt, asst));
    }

    private static class Pilot {
        private int asst;
        private int capt;

        private Pilot(int capt, int asst) {
            this.capt = capt;
            this.asst = asst;
        }

        private int salaryDiffDiff(Pilot other) {
            return (this.capt - this.asst) - (other.capt - other.asst);
        }
    }
}
