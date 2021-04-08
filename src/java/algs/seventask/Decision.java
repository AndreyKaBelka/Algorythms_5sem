package algs.seventask;

import java.util.ArrayList;
import java.util.Arrays;

public class Decision {

    private  ArrayList<Item> bestItems = new ArrayList<>();

    private double maxW;

    private double bestPrice;


    public Decision(double maxW)
    {
        this.maxW = maxW;
    }

    public void MakeAllSets(ArrayList<Item> items)
    {
        if (items.size() > 0)
            CheckSet(items);

        for (int i = 0; i < items.size(); i++)
        {
            ArrayList<Item> newSet = new ArrayList<>(items);

            newSet.remove(i);

            MakeAllSets(newSet);
        }

    }

    private void CheckSet(ArrayList<Item> items)
    {
        if (bestItems.isEmpty())
        {
            if (CalcWeight(items) <= maxW)
            {
                bestItems = items;
                bestPrice = CalcPrice(items);
            }
        }
        else
        {
            if(CalcWeight(items) <= maxW && CalcPrice(items) > bestPrice)
            {
                bestItems = items;
                bestPrice = CalcPrice(items);
            }
        }
    }


    private double CalcWeight(ArrayList<Item> items)
    {
        double sumW = 0;

        for(Item i : items)
        {
            sumW += i.weigth;
        }

        return sumW;
    }

    private double CalcPrice(ArrayList<Item> Items)
    {
        double sumPrice = 0;

        for (Item i : Items)
        {
            sumPrice += i.price;
        }

        return sumPrice;
    }
    public ArrayList<Item> GetBestSet()
    {
        return bestItems;
    }

    public static double normalPackage(int s, double[] w, double[] v, double p){
        double[][] vperw = new double[s][2];
        for (int i = 0; i < s; i++) {
            vperw[i][0] = v[i] / w[i];
            vperw[i][1] = i;
        }

        Arrays.sort(vperw, (o1, o2) -> o2[0]-o1[0]>0?1:-1);


        double tmpW = 0;
        double result = 0;
        for (int i = 0; i < s; i++) {
            int b = (int) vperw[i][1];
            double thisW = w[b];
            if (thisW < p-tmpW){
                result += v[b];
                tmpW += w[b];
                System.out.println("Предмет " + b +" взять с коэффициентом 1, чтобы получить выгоду " + v[b]);
            }else {
                double canW = p-tmpW;
                result += vperw[i][0] * canW;
                System.out.println("Предмет " + b +" Взять с коэффициентом "+ canW/thisW +" ,чтобы получить выгоду " + vperw[i][0] * canW);
                break;
            }
        }
        return result;
    }

}

