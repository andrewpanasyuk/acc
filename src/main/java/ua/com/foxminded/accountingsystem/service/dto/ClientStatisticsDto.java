package ua.com.foxminded.accountingsystem.service.dto;

/**
 * Created by andreb on 26.07.17.
 */
public class ClientStatisticsDto {

    private long activeClients;
    private long allClients;
    private long newClientsForLastMonth;
    private long frozenClients;
    private long frozenClientsForLastMonth;
    private long graduatedClients;
    private long graduatedClientsForLastMonth;

    public long getActiveClients() {
        return activeClients;
    }

    public void setActiveClients(long activeClients) {
        this.activeClients = activeClients;
    }

    public long getAllClients() {
        return allClients;
    }

    public void setAllClients(long allClients) {
        this.allClients = allClients;
    }

    public long getNewClientsForLastMonth() {
        return newClientsForLastMonth;
    }

    public void setNewClientsForLastMonth(long newClientsForLastMonth) {
        this.newClientsForLastMonth = newClientsForLastMonth;
    }

    public long getFrozenClients() {
        return frozenClients;
    }

    public void setFrozenClients(long frozenClients) {
        this.frozenClients = frozenClients;
    }

    public long getFrozenClientsForLastMonth() {
        return frozenClientsForLastMonth;
    }

    public void setFrozenClientsForLastMonth(long frozenClientsForLastMonth) {
        this.frozenClientsForLastMonth = frozenClientsForLastMonth;
    }

    public long getGraduatedClients() {
        return graduatedClients;
    }

    public void setGraduatedClients(long graduatedClients) {
        this.graduatedClients = graduatedClients;
    }

    public long getGraduatedClientsForLastMonth() {
        return graduatedClientsForLastMonth;
    }

    public void setGraduatedClientsForLastMonth(long graduatedClientsForLastMonth) {
        this.graduatedClientsForLastMonth = graduatedClientsForLastMonth;
    }
}
